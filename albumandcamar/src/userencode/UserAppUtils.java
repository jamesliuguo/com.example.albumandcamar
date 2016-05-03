/* *
 * Copyright 2002-2012 InsightCN(网言信息科技咨询有限公司)<br>
 *
 * ClassName   (类名): UserAppUtils<br>
 * Function    (功能): <br>
 * Detail      (详细): <br>
 * Version     (版本): V2.0<br>
 * Date        (日期): 2014-9-19 下午1:50:49 <br>
 * Author      (作者): 张洋 <br>
 * Description (说明): TODO(这里用一句话描述这个类的作用)  <br>
 *
 */
package userencode;


/* * 
 * @ClassName   (类名): UserAppUtils 
 * @Description (说明)：TODO(这里用一句话描述这个类的作用) 
 * @author      (作者)：张洋
 * @date        (时间)：2014-9-19 下午1:50:49  
 */
public class UserAppUtils {
	/**
	 * @Fields USERID_KEY : 用户加密的KEY
	 */
	private final static String USERID_KEY = "2@0*8ew#!d2bT4?";

	/**
	 * @Fields USERID_ALGORITHM :加密解密方式
	 */
	private final static String USERID_ALGORITHM = "Blowfish";

	/**
	 * @author (作者)：陈腾 2012-9-24 下午6:16:11
	 * @Title (方法): decryptUserID
	 * @Description(描述): 根据userID解密
	 * @param (参数)：@param userid 用户ID
	 * @param (参数)：@return 解密后的USERID
	 * @param (参数)：@throws Exception 设定文件
	 * @return String 返回类型
	 * @throws
	 */
	public static String decryptUserID(String userid) throws Exception {
		return Eryptogram.decryptOnBlowfish(USERID_KEY, userid);
	}

	/**
	 * @author (作者)：陈腾 2012-8-30 上午11:20:08
	 * @Title (方法): encryptUserID
	 * @Description(描述): 用户UserID加密
	 * @param (参数)：@param userid
	 * @param (参数)：@return
	 * @param (参数)：@throws Exception 设定文件
	 * @return String 返回类型
	 * @throws
	 */
	public static String encryptUserID(String userid) throws Exception {
		return Eryptogram.encryptData(userid, USERID_KEY, USERID_ALGORITHM);
	}

	public static void main(String[] args) {
		String sdf = "asdfasdfafdasdf";
		String es = null;
		try {
			es = encryptUserID(sdf);
			System.out.println(es);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
