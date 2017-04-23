package com.mr.media.service.authority;

import com.avaje.ebean.Ebean;
import com.avaje.ebean.ExpressionList;
import com.avaje.ebean.PagedList;
import com.mr.media.model.Admin;
import com.mr.media.model.Authority;
import com.mr.media.model.User;
import com.mr.media.request.authority.admin.DeleteAdminReq;
import com.mr.media.request.authority.admin.OperateAdminReq;
import com.mr.media.request.review.OperateReviewReq;
import com.mr.media.response.BaseResp;
import com.mr.media.response.authority.admin.GetAllAdminResp;
import com.mr.media.tool.Pair;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by i321273 on 1/6/17.
 */

@Service
public class AdminService {

    final Logger logger = LoggerFactory.getLogger(getClass());

    public Admin findAdminByUid(int uid){
        return Ebean.find(Admin.class).where()
                .eq("uid", uid)
                .findUnique();
    }

    public Pair<Integer, List<User>> getPagedEmployees(Integer pageId, Integer pageSize, Integer role, Integer disable){

        if(pageId < 0 || pageSize <= 0){
            return new Pair(new BaseResp(BaseResp.WRONG_PAGE_PARAM), null);
        }

        ExpressionList<User> el = Ebean.find(User.class).where();

        el = el.eq("role", role);
        if(disable != null){
            el = el.eq("disable", disable);
        }

        PagedList<User> pl = el.findPagedList(pageId, pageSize);

        int totalPages = pl.getTotalPageCount();
        List<User> users = pl.getList();
        return new Pair<>(totalPages, users);

    }

    public int getSubActorsNumber(String superId){

        return Ebean.find(User.class).where()
                .eq("super_id", superId)
                .eq("role", User.ACTOR_ROLE)
                .findRowCount();

    }

    public GetAllAdminResp getAllAdmin() {

        return new GetAllAdminResp(BaseResp.SUCCESS,
                Ebean.find(Admin.class).where().findList().stream().map(
                        o -> {
                            GetAllAdminResp.AdminResp adminResp = new GetAllAdminResp.AdminResp();
                            adminResp.auth = getAuthorities(o);
                            adminResp.id = o.getId();
                            adminResp.name = o.getAdmin().getRealName();
                            adminResp.phone = o.getPhoneNumber();
                            adminResp.username = o.getAdmin().getUid();
                            return adminResp;
                        }
                ).collect(Collectors.toList()));
    }


    private List<Integer> getAuthorities(Admin admin){
        List<Authority> authorities = Ebean.find(Authority.class).where().eq("admin_id", admin.getId()).findList();
        return authorities.stream().map(
                o -> {
                    return o.getAuthority();
                }
        ).collect(Collectors.toList());
    }



    private BaseResp createAdmin(OperateAdminReq operateAdminReq){
        Ebean.beginTransaction();
        Admin admin = new Admin();
        User user = new User();
        User root = Ebean.find(User.class).where().eq("id", 1).findUnique();
        if(operateAdminReq.password.equals("")){
            user.setPassword("admin1");
        }
        else{
            user.setPassword(operateAdminReq.password);
        }
        user.setRealName(operateAdminReq.name);
        user.setLevel(root.getLevel()+1);
        user.setDisable(0);
        user.setSuperUser(root);
        user.setRole(User.ADMIN_ROLE);
        user.setIdNumber("");
        user.setUid(operateAdminReq.userName);
        admin.setPhoneNumber(operateAdminReq.phoneNumber);
        admin.setAdmin(user);
        user.save();
        admin.save();
        for(Integer auth: operateAdminReq.authorities){
            Authority authority = new Authority();
            authority.setAdmin(admin);
            authority.setAuthority(auth);
            authority.save();
        }

        Ebean.commitTransaction();
        Ebean.endTransaction();
        return new BaseResp(BaseResp.SUCCESS);
    }


    private BaseResp editAdmin(OperateAdminReq operateAdminReq){
        Ebean.beginTransaction();
        Admin newAdmin = Ebean.find(Admin.class).where().eq("id", operateAdminReq.id).findUnique();
        newAdmin.getAdmin().setRealName(operateAdminReq.name);
        newAdmin.getAdmin().setPassword(operateAdminReq.password);
        newAdmin.getAdmin().setUid(operateAdminReq.userName);
        newAdmin.setPhoneNumber(operateAdminReq.phoneNumber);
        newAdmin.getAdmin().save();
        newAdmin.save();
        List<Authority> oldAuths = Ebean.find(Authority.class).where().eq("admin.id", newAdmin.getId()).findList();
        for( Authority oldAuth: oldAuths){
            oldAuth.delete();
        }
        for(Integer auth: operateAdminReq.authorities){
            Authority authority = new Authority();
            authority.setAdmin(newAdmin);
            authority.setAuthority(auth);
            authority.save();
        }
        Ebean.commitTransaction();
        Ebean.endTransaction();
        return new BaseResp(BaseResp.SUCCESS);
    }



    public BaseResp operateAdmin(OperateAdminReq operateAdminReq) {
        if(operateAdminReq.id == -1){
            return createAdmin(operateAdminReq);
        }
        else{
            return editAdmin(operateAdminReq);
        }
    }

    public BaseResp deleteAdmin(DeleteAdminReq deleteAdminReq) {
        Integer deleteId = deleteAdminReq.id;
        Admin admin = Ebean.find(Admin.class).where().eq("id", deleteId).findUnique();
        Ebean.beginTransaction();
        admin.delete();
        Ebean.commitTransaction();
        Ebean.endTransaction();
        return new BaseResp(BaseResp.SUCCESS);
    }
}
