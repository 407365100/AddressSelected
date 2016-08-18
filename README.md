# AddressSelected
收货地址选择
只是一个小的功能性的模块，没什么难点。两个只是点我其他博客有介绍<br/>
List的compare(T lhs, T rhs) :[http://blog.csdn.net/u010137760/article/details/52238998](http://blog.csdn.net/u010137760/article/details/52238998)
<br/>String的compareTo使用及释义 :[http://blog.csdn.net/u010137760/article/details/52231950](http://blog.csdn.net/u010137760/article/details/52231950)
##核心代码
```java
//对list进行排序，优先级 是否是默认助理、id
Collections.sort(addresses, new Comparator<AddressDomain>() {

     @Override
     public int compare(AddressDomain lhs, AddressDomain rhs) {
         if(lhs.getDefaultFlag().compareToIgnoreCase(rhs.getDefaultFlag())<0){
             return 1;
         }else if(lhs.getDefaultFlag().compareToIgnoreCase(rhs.getDefaultFlag())==0){
             return lhs.getId().compareToIgnoreCase(rhs.getId());
         }else{
             return -1;
         }
     }
 });
```
##效果图
![这里写图片描述](http://img.blog.csdn.net/20160818151344973)

