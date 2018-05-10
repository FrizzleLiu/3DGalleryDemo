# 3DGalleryDemo
# 实现方式基于ViewPager的clipChildren属性实现,由于ViewPager的宽度没有充满屏幕,所以ViewPager两侧看得到视图但是无法交互(滑动切换)
# 所以在空白的左右两侧各填充了透明的View(StarView自定义View拦截左右方向滑动事件),用于交互,左侧View向右滑动和点击ViewPager切换
# 右侧View向左滑动和点击切换ViewPager,其它不做处理
介绍请移步:[简书](https://www.jianshu.com/p/66247cf0a638)
