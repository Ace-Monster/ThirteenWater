#接口设计文档 Interface design document

------

##用户类 Model.User
- 构造函数 User()
    - 参数：String username, String password
- getToken()
    - 参数：None
    - 返回值：String token
- getUID()
    - 参数：None
    - 返回值：String UID
- getUsername
    - 参数：None
    - 返回值：String username

##用户登录类 Client.UserActivity

- login()
    - 参数：User user
    - 返回值：boolean, true表示登录成功，false表示登录失败
    - 调用方法：将用户名和密码注入后调用接口，登录成功则保存Token和UID
- register()
    - 参数：User user
    - 返回值：boolean, true表示注册成功，false表示注册失败
    - 调用方法：将用户名和密码注入后调用接口