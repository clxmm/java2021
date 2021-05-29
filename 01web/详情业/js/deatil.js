window.addEventListener('load',function () {
    var preview_img = document.querySelector('.preview_img');
    var mask = document.querySelector('.mask');
    var big = document.querySelector('.big');


    preview_img.addEventListener('mousemove',function () {
        mask.style.display = 'block'
        big.style.display = 'block'

    })

    preview_img.addEventListener('mouseout',function () {
        mask.style.display = 'none'
        big.style.display = 'none'

    })


    preview_img.addEventListener('mousemove',function (e) {
        // 1,鼠标在盒子内的坐标
        var x = e.pageX-this.offsetLeft;
        var y = e.pageY-this.offsetTop;
        // console.log(x+";"+y)
        var maskX = x - mask.offsetWidth/2;
        var maskY = y - mask.offsetHeight/2;
        var maskMax = preview_img.offsetWidth - mask.offsetWidth;
        if (maskX<=0) {
            maskX = 0;
        } else if (maskX>=preview_img.offsetWidth - mask.offsetWidth) {
            maskX = preview_img.offsetWidth - mask.offsetWidth;
        }

        if (maskY<=0) {
            maskY = 0
        } else if(maskY>=preview_img.offsetHeight - mask.offsetHeight){
            maskY = preview_img.offsetHeight - mask.offsetHeight
        }

        mask.style.left  = maskX+'px';
        mask.style.top  = maskY +'px';

        // 3. 大图片的移动距离 = 遮挡层移动距离 * 大图片最大移动距离 / 遮挡层的最大移动距离
        // 大图
        // 大图
        var bigIMg = document.querySelector('.bigImg');
        // 大图片最大移动距离
        var bigMax = bigIMg.offsetWidth - big.offsetWidth;
        // 大图片的移动距离 X Y
        var bigX = maskX * bigMax / maskMax;
        var bigY = maskY * bigMax / maskMax;
        console.log(bigX,bigY)
        bigIMg.style.left = -bigX + 'px';
        bigIMg.style.top = -bigY + 'px';

    })


})