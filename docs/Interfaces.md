# 接口设计文档 Interface design document

------

**注意：所有涉及到User的接口均要求Token有效，若Token失效则会被清除**

## 用户数据类 Model.User
- 构造函数 User()
    - 参数：String username, String password
- getToken()
    - 参数：None
    - 返回值：String token, 注意若返回值为null则表示该玩家已掉线
- getUID()
    - 参数：None
    - 返回值：int UID
- getUsername()
    - 参数：None
    - 返回值：String username
- logout()
    - 参数：None
    - 返回值：None
    - 作用：清除Token

## 用户登录类 Client.UserActivity

- login()
    - 参数：User user
    - 返回值：User, null表示失败
    - 调用方法：将用户名和密码注入后调用接口，登录成功则保存Token和UID
- register()
    - 参数：User user
    - 返回值：boolean, true表示注册成功，false表示注册失败
    - 调用方法：将用户名和密码注入后调用接口
- logout()
    - 参数：User user
    - 返回值：boolean, true表示注销成功，false表示注销失败
    
- **注意：该类的所有接口默认user有效，请在外部判断有效后调用**

## 排行榜数据类 Model.RankList

- getUID()
    - 参数：None
    - 返回值：int UID, 玩家ID
- getName()
    - 参数：None
    - 返回值：String Name, 玩家名
- getScore()
    - 参数：None
    - 返回值：int score, 分数

## 排行榜获取类 Client.RankActivity

- getRankList()
    - 参数：None
    - 返回值：ArrayList<RankList>, null表示失败
    
## 历史战绩数据类 Model.History

- getHID()
    - 参数：None
    - 返回值：int HID, 战局ID
- getCard()
    - 参数：None
    - 返回值：ArrayList<String> card, 玩家出牌情况
- getScore()
    - 参数：None
    - 返回值：int score, 得分情况
- getTimeStamp()
    - 参数：None
    - 返回值：int timeStamp, 该局结尾时间
    
## 历史战绩获取类 Client.HistoryActivity

- getHistoryList()
    - 参数：User user
    - 返回值：ArrayList<History>, null表示失败
    - 注意：确保user登录状态下调用该方法
- getHistory()
    - 参数：User user, int HID
    - 返回值：History, null表示失败
    - 注意：确保user登录状态以及HID有效的情况下调用该方法

## 游戏数据类