/* *
 * Copyright 2002-2012 InsightCN(网言信息科技咨询有限公司)<br>
 *
 * ClassName   (类名): UserKeyUtils<br>
 * Function    (功能): 针对用户操作的加密和解密<br>
 * Detail      (详细): 针对用户操作的加密和解密<br>
 * Version     (版本): V2.0<br>
 * Date        (日期): 2012-8-30 上午11:09:37 <br>
 * Author      (作者): 陈腾 <br>
 * Description (说明): 针对用户操作的加密和解密  <br>
 *
 */
package userencode;

/* * 
 * @ClassName   (类名): UserKeyUtils 
 * @Description (说明)：针对用户操作的加密和解密 
 * @author      (作者)：陈腾
 * @date        (时间)：2012-8-30 上午11:09:37  
 */
public class UserKeyUtils {
	/**
	 * @Fields USERID_KEY : 用户加密的KEY
	 */
	private final static String USERID_KEY = "2@04%2L#S92bT;3";

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
	 * 将pageState中的userId转化成int型
	 * @param userid
	 * @return
	 * @throws Exception
	 */
	public static String decryptUserIDToInt(String userid) throws Exception {
		return Eryptogram.decryptOnBlowfish("2@0*8ew#!d2bT4?", userid);
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
}
