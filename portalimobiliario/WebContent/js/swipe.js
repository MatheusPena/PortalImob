
//swipePlanes
//version 1.2
//by Remd


$.fn.swipePlanes = function(opciones){
    return this.each(function(){
        
        var randId = Math.round(100*Math.random()); ////IDENTIFICADOR ALEATORIO DE LA INSTANCIA////////// 

        //EL VISOR /////////////////////////////////////
        this.onselectstart = function(){return false;}; 
        this.unselectable = "on"; 
        $(this).css('-moz-user-select', 'none');
        $(this).css('-webkit-user-select', 'none');  
        $(this).css('overflow','hidden');
        
        if($(this).css('position') != 'absolute')$(this).css('position','relative');
        
        
        var anchoVisor = $(this).width();
        var altoVisor = $(this).height;
        var divVisor = this;
        
        //El CONTENEDOR /////////////////////////////////////
        var divContenedor;
        var pagActual = 0;
        
        divContenedor = $(this).children('div:first');
        divContenedor.css('transition','none');
        divContenedor.css('-moz-user-select', 'none');
        divContenedor.css('-webkit-user-select', 'none');  
        divContenedor.css('position','relative');
        divContenedor.css('left','0px');
        
        //LAS PAGINAS /////////////////////////////////////////
        var divPaginas;
        var bufferPaginas = 0;
        var metaPaginas = 0;
        var metaPagNum = 0;
        
        divPaginas = divContenedor.children('div,a');
        divPaginas.css('display','inline-block');
        divPaginas.css('float','left');
        divPaginas.css('position','relative');
        
        metaPaginas = new Array();
        metaPaginas[0] = 0;
        
        divPaginas.each(function(){
            if((bufferPaginas+(this.offsetWidth)) > anchoVisor+metaPaginas[metaPagNum]){
                if(bufferPaginas > 0)metaPagNum++;
                metaPaginas[metaPagNum] =  bufferPaginas;
            }
            
            bufferPaginas += (this.offsetWidth + parseInt($(this).css('margin-left'))+parseInt($(this).css('margin-right')));
        });
        
        metaPaginas[metaPagNum] -= anchoVisor - (bufferPaginas-metaPaginas[metaPagNum]); //Ajuste último salto
        
        divContenedor.css('width',bufferPaginas+'px'); //Ajuste Contenedor al tamano de las paginas
        
        generarControles($(this),metaPagNum);
        
        //LOS EVENTOS ////////////////////////////////////////////
        var iX = 0,aX = 0,fX = 0; //Manejo de coordenadas
        var drag = 0,t = 0,ms = 0,metaEvento,dX = 0;  //Manejo del entorno/tiempo
        
        $(this).bind('touchstart',function(event){
            clearInterval(t);
            
            if(event.pageX)iX = event.pageX;
            if(event.originalEvent.touches)iX = event.originalEvent.touches[0].pageX;
            
            divContenedor.css('transition','none');
            
            drag = 1;
            ms = 0;
            metaEvento = 'click';
            t = setInterval(function(){ms++;},1);
        });
        
        $(this).bind('touchmove',function(event){
            if(event.pageX)aX = event.pageX;
            if(event.originalEvent.touches)aX = event.originalEvent.touches[0].pageX;
            dX = Math.abs(aX - iX);
            
            if(dX > 50)event.preventDefault();
            
            
            if(drag && dX > 50){
                divContenedor.css('left',(((metaPaginas[pagActual]*-1)+(aX - iX)))+'px');
            }
            
        });
        
        $(this).bind('touchend touchcancel touchleave',function(event){
            
            if(drag && dX > 20){
                drag = 0;
                console.log(metaPaginas);
                dX = Math.abs(fX - iX);
                clearInterval(t);
                
                if(event.pageX)fX = event.pageX;
                if(event.originalEvent.touches)fX = aX;
                
                if((iX-fX) > 0 && ms < 100 && dX > 50)metaEvento = 'swipeLeft';
                if((iX-fX) <= 0 && ms < 100 && dX > 50)metaEvento = 'swipeRight';
                if((iX-fX) > 0 && ms > 100 && dX > 50)metaEvento = 'moveLeft';
                if((iX-fX) <= 0 && ms > 100 && dX > 50)metaEvento = 'moveRight';
                if(dX <= 50)metaEvento = 'click';
                
                switch(metaEvento){
                    case 'swipeLeft': 
                        if(pagActual < metaPagNum){pagActual++;} 
                        break;
                    
                    case 'moveLeft': 
                        if(pagActual < metaPagNum && dX > (Math.abs(anchoVisor/2))){pagActual++;} 
                        break;
                    
                    case 'swipeRight': 
                        if(pagActual > 0){pagActual--;} 
                        break;
                    
                    case 'moveRight': 
                        if(pagActual > 0 && dX > (Math.abs(anchoVisor/2))){pagActual--;} 
                        break;
                }
                
                ms = 0; dX = 0; iX = 0; aX = 0; fX = 0; metaEvento = '';
                
                irPagina(pagActual);
            }            
        });
        
        //EVENTOS CONTROLES /////////////////////////////////////////        
        $('.'+randId+'swipePlanesMiniPag').click(function(){
            pagActual = $(this).attr('mpg');
            irPagina();
        });
        
        $('.'+randId+'swipePlanesRowLeft').click(function(e){
            e.stopPropagation();
            e.preventDefault();
            if(pagActual > 0)pagActual--;
            irPagina();
        });
        
        $('.'+randId+'swipePlanesRowRight').click(function(e){
            e.preventDefault();
            e.stopPropagation();
            if(pagActual < metaPagNum)pagActual++;
            irPagina();
        });
        
        $(this).bind('mousemove',function(){
            $('.'+randId+'swipePlanesRowLeft').css('display','block');
            $('.'+randId+'swipePlanesRowRight').css('display','block');
            $('.'+randId+'swipePlanesRowLeft').css('opacity','1');
            $('.'+randId+'swipePlanesRowRight').css('opacity','1');
        });
        
        $('.'+randId+'swipePlanesRowLeft').bind('mousemove',function(){
            $('.'+randId+'swipePlanesRowLeft').css('opacity','1');
            $('.'+randId+'swipePlanesRowRight').css('opacity','1');
        });
        
        $('.'+randId+'swipePlanesRowRight').bind('mousemove',function(){
            $('.'+randId+'swipePlanesRowLeft').css('opacity','1');
            $('.'+randId+'swipePlanesRowRight').css('opacity','1');
        });
        
        $(this).mouseout(function(){
            $('.'+randId+'swipePlanesRowLeft').css('opacity','0');
            $('.'+randId+'swipePlanesRowRight').css('opacity','0');
        });
        
        //CONTROLES //////////////////////////////////////////////
        function generarControles(divVisor, metaPagNum){
            
                
            var i = 0;
            var miniPaginasHtml = '';
            var style = '';
            
            for(i;i <= metaPagNum; i++){
                if(!i)style='background:#FFF';
                else style='';
                miniPaginasHtml += '<div mpg="'+i+'" style="'+style+'" class="'+randId+'swipePlanesMiniPag"></div>';
            }
            
            var controlesHtml = '<div class="'+randId+'swipePlanesRowLeft"></div><div class="'+randId+'swipePlanesRowRight"></div><div class="'+randId+'swipePlanesPaginator">'+miniPaginasHtml+'</div>';
            
            divVisor.append(controlesHtml);
            
            $('.'+randId+'swipePlanesMiniPag').css('display','inline-block');
            $('.'+randId+'swipePlanesMiniPag').css('width','7px');
            $('.'+randId+'swipePlanesMiniPag').css('height','7px');
            $('.'+randId+'swipePlanesMiniPag').css('margin-left','2px');
            $('.'+randId+'swipePlanesMiniPag').css('margin-right','2px');
            $('.'+randId+'swipePlanesMiniPag').css('border','1px solid #FFF');
            $('.'+randId+'swipePlanesMiniPag').css('border-radius','4px');
            $('.'+randId+'swipePlanesMiniPag').css('cursor','pointer');
     
            $('.'+randId+'swipePlanesRowLeft').css('background-image','url(data:image/png;base64,iVBORw0KGgoAAAANSUhEUgAAADIAAABkCAYAAADE6GNbAAAABHNCSVQICAgIfAhkiAAAAAlwSFlzAAAKwwAACsMBNCkkqwAAABx0RVh0U29mdHdhcmUAQWRvYmUgRmlyZXdvcmtzIENTNui8sowAAAAWdEVYdENyZWF0aW9uIFRpbWUAMTAvMTkvMTJlCtGpAAABaElEQVR4nO3cS3HEMBRE0atU1oaURTCIwxiSh4NQBNIQcDDEfp/OVF8AKp3yR5IXHsD3GIP/3HmefI4xOI6jey632vedj+5JRGWIWoaoZYhahqhliFqGqGWIWoaoZYhahqhliFqGqGWIWoZENed8RIzTCplzPrZt+4oYqw0SiYAmSDQCGiAZCCiGZCGgEJKJgCJINgIKIBUISIZUISARUomAJEg1AhIgHQgIhnQhIBDSiYAgSDcCAiAKCLgJUUHADYgSAi5C1BBwAaKIAIGPD1H9GbLWer5er5+Mydzp0hVRxFy+tdQwt54RJczth10FE/LWUsCEvX67MaHrSCcmfEHswqSs7B2YtC1KNSZ1r1WJSd80VmFKdr8VmLJtfDam9DySiSk/WGVhWk6IGZi2o240pvXMHolp//iw1npGjNMOicoQtQxRyxC1DFHLELUMUcsQtQxRyxC1DFHLELUMUettIIM3+XXbL64qrlAHZEgkAAAAAElFTkSuQmCC)');
            $('.'+randId+'swipePlanesRowLeft').css('position','absolute');
            $('.'+randId+'swipePlanesRowLeft').css('width','50px');
            $('.'+randId+'swipePlanesRowLeft').css('height','100px');
            $('.'+randId+'swipePlanesRowLeft').css('cursor','pointer');
            $('.'+randId+'swipePlanesRowLeft').css('left','0px');
            $('.'+randId+'swipePlanesRowLeft').css('top',((divVisor.height()/2)-50)+'px');
            $('.'+randId+'swipePlanesRowLeft').css('z-index','909');
            $('.'+randId+'swipePlanesRowLeft').css('display','none');
            $('.'+randId+'swipePlanesRowLeft').css('box-shadow','0 1px 3px rgba(0, 0, 0, 0.3);');
            
            $('.'+randId+'swipePlanesRowRight').css('background-image','url(data:image/png;base64,iVBORw0KGgoAAAANSUhEUgAAADIAAABkCAYAAADE6GNbAAAABHNCSVQICAgIfAhkiAAAAAlwSFlzAAAKwwAACsMBNCkkqwAAABx0RVh0U29mdHdhcmUAQWRvYmUgRmlyZXdvcmtzIENTNui8sowAAAAWdEVYdENyZWF0aW9uIFRpbWUAMTAvMTkvMTJlCtGpAAABXklEQVR4nO3cvW2EQBRF4TPWxpTkwDXQw1IS7oEqXBINsKlTM+/nenVPDuKTYOZNwgC+xhj8567r4jHGYN/37meZats2ProfIipD1DJELUPUMkQtQ9QyRC1D1DJELUPUMkQtQ9QyRC1D1DLkd+u6PiPuM1MIZFmWz25M2KvVjQn9Rjox4R97FyZl1erApC2/1ZjUfaQSk74hVmFKdvYKTNmIko0pnbUyMeVDYxamZfrNwLSN8dGY1vNIJKb9YBWFaYdADEYCAvMYGQjMYaQgcB8jB7mbHOQ8z5/jOL7/ep0U5C4ChCAzCBCBzCJAABKBgGZIFAIaIZEIaIJEI6ABkoGAYkgWAgohmQgogmQjoABSgYBkSBUCEiGVCEiCVCMgAdKBgGBIFwICIZ0ICIJ0IyAI0o0AgYNVVIaoZYhahqhliFqGqGWIWoaoZYhahqhliFqGqGWIWm8DGbzJr9teHYWuULzgkDAAAAAASUVORK5CYII=)');
            $('.'+randId+'swipePlanesRowRight').css('position','absolute');
            $('.'+randId+'swipePlanesRowRight').css('width','50px');
            $('.'+randId+'swipePlanesRowRight').css('height','100px');
            $('.'+randId+'swipePlanesRowRight').css('cursor','pointer');
            $('.'+randId+'swipePlanesRowRight').css('right','0px');
            $('.'+randId+'swipePlanesRowRight').css('top',((divVisor.height()/2)-50)+'px');
            $('.'+randId+'swipePlanesRowRight').css('z-index','909');
            $('.'+randId+'swipePlanesRowRight').css('display','none');
            $('.'+randId+'swipePlanesRowRight').css('box-shadow','0 1px 3px rgba(0, 0, 0, 0.3);');
            
            $('.'+randId+'swipePlanesPaginator').css('background-color','rgba(0,0,0,0.16)');
            $('.'+randId+'swipePlanesPaginator').css('position','absolute');
            $('.'+randId+'swipePlanesPaginator').css('padding','0px 4px');
            $('.'+randId+'swipePlanesPaginator').css('bottom','10px');
            $('.'+randId+'swipePlanesPaginator').css('box-shadow','0 1px 3px rgba(0, 0, 0, 0.3);');
            $('.'+randId+'swipePlanesPaginator').css('border-radius','4px');
            $('.'+randId+'swipePlanesPaginator').css('left',((divVisor.width()/2)-($('.'+randId+'swipePlanesPaginator').width()/2))+'px');
    
        }
        
        function irPagina(){
            divContenedor.css('transition','left 500ms');
            divContenedor.css('left',((metaPaginas[pagActual])*-1)+'px');
            
            $('.'+randId+'swipePlanesMiniPag').css('background','');
            $('.'+randId+'swipePlanesMiniPag[mpg='+pagActual+']').css('background','#FFF');
        }
    });
};
