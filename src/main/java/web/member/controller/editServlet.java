package web.member.controller;

import web.member.pojo.MemberData;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import java.util.Base64;

import static core.util.CommonUtil.json2Pojo;
import static core.util.CommonUtil.writePojo2Json;
import static web.member.util.MemberConstants.SERVICE;
@WebServlet("/member/edit")
public class editServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) {

        MemberData memberData = json2Pojo(request, MemberData.class);
        if (memberData == null) {
            memberData = new MemberData();
            memberData.setMessage("無會員資訊");
            memberData.setSuccessful(false);
            writePojo2Json(response, memberData);
            return;
        }
        //把圖片從base64轉型成byte[]
        if(memberData.getMemberPic4json()!= null){
            byte[] MemberPic = Base64.getDecoder().decode(memberData.getMemberPic4json());
            memberData.setMemberPic(MemberPic);
            memberData.setMemberPic4json("");
        }

        memberData = SERVICE.edit(memberData);

        if (memberData.isSuccessful()) {
            if (request.getSession(false) != null) {
                request.changeSessionId();
            }
            if(memberData.getMemberPic()!=null){
                memberData.setMemberPic4json("有圖");
            }
        }
        writePojo2Json(response, memberData);
    }
}
