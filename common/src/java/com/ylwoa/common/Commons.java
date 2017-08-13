package com.ylwoa.common;

import com.google.common.base.Charsets;
import com.google.common.base.Preconditions;
import com.google.common.base.Splitter;
import com.google.common.hash.Hashing;
import com.ylwoa.model.User;

import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * Created by wubiqing on 2017/7/15.
 */
public class Commons {
    public static final int ACTIVE_STATE = 0;
    public static final int INACTIVE_STATE = 1;//删除flg
    public static final String USER_COOKIE_KEY = "uid";
    public static final String USER_SESSION_MARK = "USER_SESSION_MARK";
    public static final String DELETE_FLG = "deleteFlg";
    public static final String DATE_RANGE_FORMAT = "yyyy-MM-dd";


    public static String maskForCookie(String str) {
        String MASK_FOR_COOKIE = "rdfEk83.ejK.IMnBdU";
        return Hashing.sha256().hashString(MASK_FOR_COOKIE + str, Charsets.UTF_8).toString();
    }

    public static String maskForDB(String str) {
        String MASK_FOR_DB = "W2jN.OibW73Pmaf0EJ";
        return Hashing.sha256().hashString(MASK_FOR_DB + str, Charsets.UTF_8).toString();
    }

    public static DateRange parseDateRange(String strRange) throws ParseException {
        List<String> dateStrings = Splitter.on(" - ").splitToList(strRange);
        if (dateStrings.size() != 2) {
            throw new ParseException(strRange, 0);
        }
        DateRange ret = new DateRange();
        SimpleDateFormat dft = new SimpleDateFormat(DATE_RANGE_FORMAT);
        ret.setStart(dft.parse(dateStrings.get(0)));
        ret.setEnd(dft.parse(dateStrings.get(1)));
        return ret;
    }

    public static User getUserInfoFromSession(ServletRequest request) {
        User user = null;
        HttpServletRequest servletRequest = (HttpServletRequest) request;
        HttpSession session = servletRequest.getSession(false);
        if (null != session) {
            user = (User) session.getAttribute(USER_SESSION_MARK);
        }
        Preconditions.checkNotNull(user, "get session null");
        return user;
    }


    public static class DateRange {
        private Date start;
        private Date end;

        public Date getStart() {
            return start;
        }

        public void setStart(Date start) {
            this.start = start;
        }

        public Date getEnd() {
            return end;
        }

        public void setEnd(Date end) {
            this.end = end;
        }
    }

}
