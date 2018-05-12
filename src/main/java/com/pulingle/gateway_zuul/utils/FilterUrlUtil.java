package com.pulingle.gateway_zuul.utils;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by @杨健 on 2018/5/10 16:19
 *
 * @Des: 过滤地址工具类
 */

public class FilterUrlUtil {
    public static List<String> getUrlList(){
        List<String> urllist = new ArrayList<String>();
        //moment-service
        urllist.add("/moment-service/moment/publish");
        urllist.add("/moment-service/comment/thumb");
        urllist.add("/moment-service/comment/remark");
        urllist.add("/moment-service/moment/queryFriendMomentByUserId");
        urllist.add("/moment-service/moment/queryRecommendMoments");
        //user-service
        urllist.add("/user-service/acceptFriendRequest");
        urllist.add("/user-service/deleteFriend");
        urllist.add("/user-service/user/updateUserInfo");
        urllist.add("/user-service/user/saveProfilePicture");
        urllist.add("/user-service/user/queryFriendMomentStatus");
        //picture-service
        urllist.add("/zuul/picture-service/picture/upload");
        urllist.add("/picture-service/picture/upload");
        urllist.add("/picture-service/picture/queryAlbumPictureByForWall");
        urllist.add("/picture-service/picture/queryNewAlbumPicture");
        //message-service
        urllist.add("/message-service/message/send");
        urllist.add("/message-service/message/sendFriendRequest");
        urllist.add("/message-service/message/queryMessage");
        urllist.add("/message-service/message/getMessageRecords");

        return urllist;
    }
}
