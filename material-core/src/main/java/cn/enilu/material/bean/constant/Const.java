package cn.enilu.material.bean.constant;

/**
 * 系统常量
 *
 * @author fengshuonan
 * @date 2017年2月12日 下午9:42:53
 */
public interface Const {
    long SYSTEM_USER_ID=-1;

    /**
     * 用户密码加密key
     */
    String CRYPT_DES_KEY = "sc123456";
    /**
     * 系统默认的管理员密码
     */
    String DEFAULT_PWD = "111111";

    /**
     * 管理员角色的名字
     */
    String ADMIN_NAME = "administrator";

    /**
     * 管理员id
     */
    Integer ADMIN_ID = 1;

    /**
     * 超级管理员角色id
     */
    Integer ADMIN_ROLE_ID = 1;

    /**
     * 接口文档的菜单名
     */
    String API_MENU_NAME = "接口文档";

    interface RESPONSE_RCODE{
        String SUCCESS="0";
        String ERROR="1";
    }

}
