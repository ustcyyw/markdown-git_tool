### markdown_tool思路

#### md的固定格式：

* 支持md语法的软件/网页会将固定格式的字符串渲染成对应的格式。
* 所以我们就可以利用这一点，查看格式对应的字符串，然后将你需要的格式以字符串的形式写入md文件即可。
* 我使用typora这个软件的源代码模式来看格式与字符串的对应关系。

#### 配置加载

* `StringSetting`中进行了多种格式对应字符串的设置。
  
    * 比如“两种解法”这一个标题格式对应的字符串为`private static String mulSolve = "\n### %d种解法"`，这里%d是为了填入具体几种解法
    * 在这个程序中还要设置文件的编码，比如我需要写入中文，所以我的编码设置为`String encoding = "GB2312";`，之后写入文件的时候需要用到。
    * 如果需要写入什么其他内容，就自定义一些字符串进去就好。
    
* `StringSetting`还可以从文件config.txt中读取配置

    * ```java
        private final static String CONFIG_FILE = "config.txt";
        private static Map<String, String> loadSetting();
        public static void setSetting();
        ```

    * 对于最重要的三项：md文件输出目录/前置广告位内容/后置广告位内容。可以通过配置文件的方式进行修改。如果没有配置文件或者配置文件错误，则使用默认值（输出目录为程序jar包所在目录，广告位则显示招租中）。

    * 修改的时候注意：

        * 配置项不允许修改。文件输出目录 path；前置广告 adv1；后置广告 adv2。并且配置项与值之间用等号连接，不能有多余空格。
        * 配置项之间不能有空行。
        * 前置广告固定单行；后置广告可以多行，每一行的内容使用分号（;）分割。
    
    * 配置及效果示例图如下：
    
        <img src="https://github.com/ustcyyw/markdown-git_tool/blob/master/picture/config.jpg?raw=true?raw=true" alt="config.png" style="zoom:80%;" /> 
    
* 小程序入口`APPEnter`

  ```java
  static {
          StringSetting.setSetting();
      }
  ```

  * 使用静态代码块，在入口类加载的时候，将配置文件的内容读取到`StringSetting`中。

#### 生成md文件：

* `MdWriter.java`，将`StringSetting.java`中设置好的字符串，按照你的格式逻辑依次写入文件即可。
    * 使用类`PrintWriter writer`
    * 字符串的写入等等就按照你想生成的格式依次写入即可。在`StringSetting.java`设置字符串时已经添加了换行符，这里就不需要进行了。

#### 小程序制作：

* GUI设计，java学这个属实很傻，所以就不详细说：

    * 把你需要的组件对象添加到你的窗口，为你的组件添加动作监听。
    * 注意异常处理，就是处理不合法输入，然后弹窗提示修改。
    * 小程序入口`APPEnter.java`，涉及GUI的固定写法。

* 使用maven进行打包，这个过程百度or谷歌。在jar包目录下执行`java -jar <包名>`；或者执行绝对路径也行。

* 异常处理：防止非法输入，一个简单的类`LittleException`

    * 继承`Exception`，重写`getMessage`方法，在错误信息后面添加上”请修改输入“
    * 处理四个异常，以枚举常量到形式罗列：
    
    ```java
    public enum ExceptionMessage {
        FILE_ERROE("路径设置或者文件名格式错误"),
        ENCODING_ERROR("编码设置有错误"),
        NUM_FORMAT_ERROR("数字输入格式错误"),
        LOAD_CONFIG_ERROR("初始化配置错误");
    
        public String message;
    
        ExceptionMessage(String message){
            this.message = message;
        }
    }
    ```
    
    