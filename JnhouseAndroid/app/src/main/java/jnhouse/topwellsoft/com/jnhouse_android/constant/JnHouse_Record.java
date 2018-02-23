package jnhouse.topwellsoft.com.jnhouse_android.constant;

import com.topwellsoft.jnhouse_android.constant.GlobalParas;

/**
 * Created by admin on 2016/5/26.
 */
public interface JnHouse_Record {

    public final static String Key_login = GlobalParas.URL + GlobalParas.apiClass + "/user/login";//用户登录

    public final static String Key_send_code = GlobalParas.URL + GlobalParas.apiClass + "/user/send_code";//验证用户名并发送验证码

    public final static String Key_register = GlobalParas.URL + GlobalParas.apiClass + "/user/register";//注册

    public final static String Key_pwd_reset = GlobalParas.URL + GlobalParas.apiClass + "/user/pwd_reset";//密码重置

    public final static String Key_home_index = GlobalParas.URL + GlobalParas.apiClass + "/home/index";//首页信息

    public final static String Key_second_home_index = GlobalParas.URL + GlobalParas.apiClass + "/sec_house/index";//二手房列表

    public final static String Key_second_home_list = GlobalParas.URL + GlobalParas.apiClass + "/sec_house/list";//二手房分页查询

    public final static String Key_second_home_detail = GlobalParas.URL + GlobalParas.apiClass + "/sec_house/detail";//二手房详情页

    public final static String Key_second_home_favor = GlobalParas.URL + GlobalParas.apiClass + "/base/favor";//关注/取消关注

    public final static String Key_decoration = GlobalParas.URL + GlobalParas.apiClass + "/home/dec_apply";// 装修

    public final static String Key_rent_house_index = GlobalParas.URL + GlobalParas.apiClass + "/rent_house/index";//租房列表

    public final static String Key_rent_house_list = GlobalParas.URL + GlobalParas.apiClass + "/rent_house/list";//租房列表筛选

    public final static String Key_rent_house_detail = GlobalParas.URL + GlobalParas.apiClass + "/rent_house/detail";//租房详情

    public final static String Key_new_house_index = GlobalParas.URL + GlobalParas.apiClass + "/new_house/index";//新房列表

    public final static String Key_new_house_list = GlobalParas.URL + GlobalParas.apiClass + "/new_house/list";//新房分页筛选

    public final static String Key_new_house_detail = GlobalParas.URL + GlobalParas.apiClass + "/new_house/detail";//新房详情

    public final static String Key_buy_house_guide = GlobalParas.URL + GlobalParas.apiClass + "/home/gfzn_init";//咨询列表

    public final static String Key_buy_house_guide_detail = GlobalParas.URL + GlobalParas.apiClass + "/home/gfzn_detail";//咨询详情

    public final static String Key_my_favor = GlobalParas.URL + GlobalParas.apiClass + "/my/user_favor";//我的关注

    public final static String Key_search = GlobalParas.URL + GlobalParas.apiClass + "/house/bor_search";//小区搜索

    public final static String Key_my_history = GlobalParas.URL + GlobalParas.apiClass + "/my/user_history";//我的浏览

    public final static String Key_mine_sell = GlobalParas.URL + GlobalParas.apiClass + "/publish_sell/house_list";//我的出售委托列表


    public final static String Baike_init = GlobalParas.URL + GlobalParas.apiClass + "/home/baike_init";//百科初始化

    public final static String Baike_List = GlobalParas.URL + GlobalParas.apiClass + "/home/baike_list";//百科列表

    public final static String Baike_Detail = GlobalParas.URL + GlobalParas.apiClass + "/home/baike_detail";//百科详情

    public final static String message = GlobalParas.URL + GlobalParas.apiClass + "/base/message";//留言

    public final static String report = GlobalParas.URL + GlobalParas.apiClass + "/base/report";//虚假房源举报

    public final static String Wd_Index = GlobalParas.URL + GlobalParas.apiClass + "/home/wd_index";//问答首页

    public final static String Wd_List = GlobalParas.URL + GlobalParas.apiClass + "/home/wd_list";//问答列表页

    public final static String My_Ask = GlobalParas.URL + GlobalParas.apiClass + "/base/my_ask";//我的提问

    public final static String Do_Ask = GlobalParas.URL + GlobalParas.apiClass + "/base/do_ask";//我要提问

    public final static String Wd_Detail = GlobalParas.URL + GlobalParas.apiClass + "/home/wd_detail";//问答详情页 /base/do_praise 方式

    public final static String Do_Praise = GlobalParas.URL + GlobalParas.apiClass + "/base/do_praise";//问答详情页 /base/ask_del

    public final static String Ask_Del = GlobalParas.URL + GlobalParas.apiClass + "/base/ask_del";//删除提问

    public final static String Look_Record = GlobalParas.URL + GlobalParas.apiClass + "/house/look_record";//看房列表

    public final static String Broker_List = GlobalParas.URL + GlobalParas.apiClass + "/home/broker_list";//经纪人列表

    public final static String Broker_Card = GlobalParas.URL + GlobalParas.apiClass + "/home/broker_card";//经纪人详情

    public final static String Eveluate = GlobalParas.URL + GlobalParas.apiClass + "/base/new_house_evaluate";//新房评价

    public final static String Call_Counter = GlobalParas.URL + GlobalParas.apiClass + "/new_house/call_number";//拨打电话计数

    public final static String Find_User = GlobalParas.URL + GlobalParas.apiClass + "/user/find_user";//根据id查询用户信息

    public final static String Un_Reset = GlobalParas.URL + GlobalParas.apiClass + "/user/un_reset";//用户名重置

    public final static String Mine_rent = GlobalParas.URL + GlobalParas.apiClass + "/rent/rent_list";//出租委托列表

    public final static String Mine_want = GlobalParas.URL + GlobalParas.apiClass + "/rent/w_rent_list";//求租房委托列表

    public final static String Mine_buy = GlobalParas.URL + GlobalParas.apiClass + "/publish_sell/want_buy_list";//求购委托列表

    public final static String Rent_init = GlobalParas.URL + GlobalParas.apiClass + "/rent/w_rent_init";//配套设施初始化

    public final static String Key_Lease = GlobalParas.URL + GlobalParas.apiClass + "/rent/rent";//租房委托发布

    public final static String Key_sell_second = GlobalParas.URL + GlobalParas.apiClass + "/publish_sell/sell_second";//二手房出售委托发布

    public final static String Key_sell_office = GlobalParas.URL + GlobalParas.apiClass + "/publish_sell/sell_buiding";//写字楼出售委托发布

    public final static String Key_sell_store = GlobalParas.URL + GlobalParas.apiClass + "/publish_sell/sell_shop";//商铺出售委托发布

    public final static String Key_buy = GlobalParas.URL + GlobalParas.apiClass + "/publish_sell/want_buy";//购房需求发布

    public final static String Key_rent = GlobalParas.URL + GlobalParas.apiClass + "/rent/w_rent";//租房需求发布

    public final static String Key_user_avatar = GlobalParas.URL + GlobalParas.apiClass + "/my/user_avatar";//修改用户个人头像

    public final static String Key_supporting_facilities = GlobalParas.URL + GlobalParas.apiClass + "/rent/w_rent_init";//配套设施初始化

    public final static String Key_user_info = GlobalParas.URL + GlobalParas.apiClass + "/my/user_info";//用户个人信息

    public final static String Key_del_my_history = GlobalParas.URL + GlobalParas.apiClass + "/my/del_history";//删除我的浏览

    public final static String Key_del_my_favor = GlobalParas.URL + GlobalParas.apiClass + "/my/del_favor";//删除我的关注

    public final static String Key_del_my_sell = GlobalParas.URL + GlobalParas.apiClass + "/publish_sell/house_delete";//出售列表的删除

    public final static String Key_del_my_buy = GlobalParas.URL + GlobalParas.apiClass + "/publish_sell/want_buy_delete";//求购列表的删除

    public final static String Key_del_my_rent = GlobalParas.URL + GlobalParas.apiClass + "/rent/rent_delete";//出租房屋列表的删除

    public final static String Key_del_my_want = GlobalParas.URL + GlobalParas.apiClass + "/rent/w_rent_delete";//求租房列表的删除 /home/index

    public final static String Key_Borough_Detail = GlobalParas.URL + GlobalParas.apiClass + "/house/detail";  //  /schedule/house_schedule_list_u

    public final static String House_schedule_list_u = GlobalParas.URL + GlobalParas.apiClass + "/schedule/house_schedule_list_u";//日程列表

    public final static String House_schedule_detail = GlobalParas.URL + GlobalParas.apiClass + "/schedule/house_schedule_detail";//日程详情

    public final static String Order_Init = GlobalParas.URL + GlobalParas.apiClass + "/order/order_init";//检查用户状态

    public final static String Customer_Order = GlobalParas.URL + GlobalParas.apiClass + "/order/customer_order";// 用户预约

    public final static String Order_cancel = GlobalParas.URL + GlobalParas.apiClass + "/order/order_cancel"; //订单（日程）取消

    public final static String C_order_detail = GlobalParas.URL + GlobalParas.apiClass + "/order/c_order_detail"; //用户下单预约中详情页

    public final static String runChart=GlobalParas.URL +"/mobile/runChart.html?";//走势图

    public final static String Key_push_broker = GlobalParas.URL + GlobalParas.apiClass + "/home/do_push"; //推送经纪人

    public final static String Financial=GlobalParas.URL+"/mobile/finance.html";//金融页面

    public final  static String About=GlobalParas.URL+"/mobile/about.html";//关于我们

    public final  static String Opinion=GlobalParas.URL+"/mobile/feedback.html";//意见反馈

    public  final static String InvitFriend=GlobalParas.URL+"/mobile/invite.html";//邀请好友
}
