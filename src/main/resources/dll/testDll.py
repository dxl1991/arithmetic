# -*- coding: UTF-8 -*-
import sys
# sys.path.append('D:\\ruanjian\\python2.7.14\\Lib\\site-packages')
# sys.path.append('D:\\ruanjian\\python2.7.14\\Lib')

print(sys.path)

import clr
# clr.FindAssembly("JNATest.dll")

clr.AddReference("JNATest") # 加载c#dll文件
 
from JNATest import * # 导入命名空间
 
instance = JNA() # 实例化dll里面的OpenSign类
 

def add(a,b):
  c = instance.getAge(a)
  return c
  
print(instance.getName(sys.argv[1]))