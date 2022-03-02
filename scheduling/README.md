关注微信公众号【江南一点雨】，回复 666 有惊喜～

定时任务也算是我们日常开发中比较常见的需求了，市面上也有很多成熟的框架：

- quartz
- elastic-job
- xxl-job
- ...

不过小伙伴们知道，其实我们 Spring 框架中也提供了相应的定时任务，这个定时任务通过 `@EnableScheduling` 注解开启，松哥之前也写过文章和大家分享这个注解的基本用法：

- [Spring Boot 中实现定时任务的两种方式!](https://mp.weixin.qq.com/s/_20RYBkjKrB4tdpXI3hBOA)

不过之前的定时任务都是固定的，提前写死的，没法动态配置，前两天有小伙伴问松哥如何实现定时任务的动态配置？

这个东西要是基于 xxl-job 之类的框架来做其实是比较容易的，不过用 Spring 自带的 `@EnableScheduling` 注解其实也能实现，而且并不难，松哥基于此火急火燎的写了一个，今天先和大家聊聊大致用法，后面抽空再写一篇文章介绍实现原理。

项目已开源，项目地址：

- [https://github.com/lenve/scheduling](https://github.com/lenve/scheduling)

## 食用方式

1. 克隆项目：`git clone https://github.com/lenve/scheduling.git`。
2. 本地数据库创建一个名为 `scheduling` 的库。
3. 修改配置文件 `src/main/resources/application.yaml`，主要修改数据库连接的用户名和地址。
4. 启动项目。
5. 浏览器访问 `http://localhost:8080`，可以看到如下页面：

![](http://img.itboyhub.com/2021/07/20210910174209.png)

表示启动成功。

## 功能介绍

1. 项目启动时，会自动从数据库中加载状态为 1 的定时任务并开始执行，1 表示处于开启状态的定时任务，0 表示处于禁用状态的定时任务。
2. 点击页面上的**添加作业**按钮，可以添加一个新的定时任务，新任务的 Bean 名称、方法名称以及方法参数如果和已有的记录相同，则认为是重复作业，重复作业会添加失败。

添加作业的页面如下：

![](http://img.itboyhub.com/2021/07/20210910181820.png)

这里涉及到几个参数，含义如下：

- Bean 名称：这是项目中注入 Spring 的 Bean 名称，测试代码中以 `org/javaboy/scheduling02/service/SchedulingTaskDemo.java` 为例。
- 方法名称：参数 1 中 bean 里边的方法名称。
- 方法参数：参数 2 中方法的参数。
- Cron 表达式：定时任务的 Cron 表达式。
- 作业状态：开启和禁用两种。开启的话，添加完成后这个定时任务就会开始执行，禁用的话，就单纯只是将记录添加到数据库中。


作业添加成功提示如下：

![](http://img.itboyhub.com/2021/07/20210910182636.png)

作业添加失败提示如下：

![](http://img.itboyhub.com/2021/07/20210910181458.png)

3. 点击作业编辑，可以修改作业的各项数据：

![](http://img.itboyhub.com/2021/07/20210910182736.png)

修改后会立马生效。

4. 点击作业删除，可以删除一个现有的作业。假如删除的作业正在执行，则先停止该作业，然后删除。
5. 点击列表中的 switch 按钮也可以切换作业的状态。

![](http://img.itboyhub.com/2021/07/20210910183133.png)

## 技术栈

- SpringBoot
- Jpa
- MySQL
- Spring Job
- Vue

## 其他

这是一个学习的 Demo，并非完整项目，后面松哥会出一篇文章和大家分享具体的实现思路。

好啦，先说这么多。

感兴趣的小伙伴赶紧去体验一把吧：[https://github.com/lenve/scheduling](https://github.com/lenve/scheduling)