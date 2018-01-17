package com.test.app.Controller;


import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.sun.xml.internal.ws.resources.HttpserverMessages;
import com.test.app.Entity.GearAndMaterial.Gear;
import com.test.app.Entity.GearAndMaterial.GearStatus;
import com.test.app.Entity.GearAndMaterial.Material;
import com.test.app.Entity.Login.LoginRecords;
import com.test.app.Entity.Sector.Unit;
import com.test.app.Entity.User.User;
import com.test.app.Entity.User.UserAuth;
import com.test.app.Entity.User.UserJob;
import com.test.app.Service.LoginServices.LoginService;
import com.test.app.Service.MaterialAndGearService;
import com.test.app.Service.UnitServices.UnitService;
import com.test.app.Service.UserServices.UserAuthService;
import com.test.app.Service.UserServices.UserJobService;
import com.test.app.Service.UserServices.UserService;
import com.test.app.Utils.Json;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Controller
@RequestMapping(value = "/springboot")
public class APIController<T> {
    @Autowired
    private UserService userService;
    @Autowired
    private UserAuthService userAuthService;
    @Autowired
    private MaterialAndGearService materialAndGearService;
    @Autowired
    private LoginService loginService;
    @Autowired
    private UserJobService userJobService;
    @Autowired
    private UnitService unitService;
    private final ResourceLoader resourceLoader;
    @Autowired
    public APIController(ResourceLoader resourceLoader) {
        this.resourceLoader = resourceLoader;
    }
    public ResourceLoader getResourceLoader() {
        return resourceLoader;
    }

    @RequestMapping(value = "/testLogin", method = RequestMethod.POST)
    @ResponseBody
    Object TestLogin () {
        return null;
    }
    @RequestMapping(value = "/login", method = RequestMethod.POST)
    @ResponseBody
    Object Login (@RequestBody JSONObject login, HttpSession session, HttpServletRequest servletRequest) throws Exception {
        String accout = login.getString("account");
        String password = login.getString("password");
        List<UserAuth> list = userService.validate(accout, password);
        LoginRecords loginRecords = new LoginRecords();
        loginRecords.setAccount(accout);
        loginRecords.setSession(session.getId());
        loginRecords.setIp(servletRequest.getRemoteAddr());
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        loginRecords.setTime(df.format(new Date()));
        loginService.setOne(loginRecords);
        if (list.size() != 0) {
            JSONObject js = (JSONObject)Json.parseJson(list.get(0).getUser());
            session.setAttribute(session.getId(), list.get(0).getAccount());
            return js;
        } else {
            throw  new RETCode(106);
        }
    }
    @RequestMapping(value = "/init", method = RequestMethod.POST)
    @ResponseBody
    Object Init (HttpSession session) throws Exception {
        String account = (String)session.getAttribute(session.getId());
        if (account != null) {
            JSONObject jsonObject;
            User user = userAuthService.findByAccount(account).get(0).getUser();
            jsonObject = (JSONObject)Json.parseJson(user);
            jsonObject.put("account", userAuthService.findByAccount(account).get(0).getAccount());
            return jsonObject;
        } else {
            throw new RETCode(101);
        }
    }
    @RequestMapping(value = "/logout", method = RequestMethod.POST)
    @ResponseBody
    Object Logout (@RequestBody JSONObject form, HttpSession session) throws Exception {
        String account = form.getString("account");
        if (account.equals(session.getAttribute(session.getId()))) {
            session.removeAttribute(session.getId());
        }
        return null;
    }
    @RequestMapping(value = "/upload")
    @ResponseBody
    Object upload (@RequestBody MultipartFile file) throws Exception {
        if (file.isEmpty()) {
            throw new RETCode(100);
        }
        String fileName = file.getOriginalFilename();
        String suffixName = fileName.substring(fileName.lastIndexOf('.'));
        String path = "C:\\Users\\Administrator\\IdeaProjects\\zxcWebtest\\WEB-INF\\pics\\";
        File dest = new File(path + fileName);
        if (!dest.getParentFile().exists()) {
            dest.getParentFile().mkdirs();
        }
        file.transferTo(dest);
        return "上传成功";
    }
//    @RequestMapping(value = "/pics/{filename:.+}", method = RequestMethod.GET)
//    @ResponseBody
//    Object GetPic (@PathVariable String filename) throws Exception {
//        System.out.println(filename);
//        return resourceLoader.getResource("file:C:\\Users\\Administrator\\IdeaProjects\\zxcWebtest\\WEB-INF\\pics\\" + filename);
//    }
    @RequestMapping(value = "/user/updateInfo", method = RequestMethod.POST)
    @ResponseBody
    Object UpdateInfo (@RequestBody JSONObject form, HttpSession session) throws Exception {
        preHandle(session);
        User user = userService.findById(form.getLong("id"));
        user.setAge(form.getInteger("age"));
        user.setEmail(form.getString("email"));
        user.setFix(form.getString("fix"));
        user.setGender(form.getString("gender"));
        user.setRealName(form.getString("realName"));
        user.setTel(form.getString("tel"));
        userService.setOne(user);
        return user;
    }

    @RequestMapping(value = "/user/updatePass", method = RequestMethod.POST)
    @ResponseBody
    Object UpdatePass (@RequestBody JSONObject form) throws Exception {
        User user = userService.findById(form.getLong("id"));
        UserAuth userAuth = user.get();
        userAuth.setPassword(form.getString("password"));
        userAuthService.setOne(userAuth);
        return user;
    }
    @RequestMapping(value = "/user/deleteUser", method = RequestMethod.POST)
    @ResponseBody
    Object DeleteUser (@RequestBody JSONObject form) throws Exception {
        Long id = form.getLong("id");
        User user = userService.findById(id);
        UserJob userjob = user.getUserJob();
        Unit unit = userjob.getUnit();
        unit.removeUser(userjob);
        if (userjob.getManager() != null) {
            Unit manage = userjob.getManager();
            manage.setResponsible(null);
            unitService.setOne(manage);
        }
        unitService.setOne(unit);
        userService.remove(user);
        return null;
    }

    @RequestMapping(value = "/dataBaseManager/test", method = RequestMethod.POST)
    @ResponseBody
    Object TestDB (HttpSession session) throws Exception{
        UserAuth auth = userAuthService.findByAccount((String)session.getAttribute(session.getId())).get(0);
        if (!auth.getUser().getRole().getRole().equals("dataBaseManager")) {
            throw new RETCode(105);
        }
        return null;
    }

    @RequestMapping(value = "/militaryBase/test", method = RequestMethod.POST)
    @ResponseBody
    Object TestMB (HttpSession session) throws Exception{
        UserAuth auth = userAuthService.findByAccount((String)session.getAttribute(session.getId())).get(0);
        if (!auth.getUser().getRole().getRole().equals("militaryBase")) {
            throw new RETCode(105);
        }
        return null;
    }
    @RequestMapping(value = "/centralManager/test", method = RequestMethod.POST)
    @ResponseBody
    Object TestCM (HttpSession session) throws Exception{
        UserAuth auth = userAuthService.findByAccount((String)session.getAttribute(session.getId())).get(0);
        if (!auth.getUser().getRole().getRole().equals("centralManager")) {
            throw new RETCode(105);
        }
        return null;
    }
    @RequestMapping(value = "/user/getRoles", method = RequestMethod.POST)
    @ResponseBody
    Object GetRoles () throws Exception {
        return Json.parseJson(userService.getRoles());
    }

    @RequestMapping(value = "/user/addUser", method = RequestMethod.POST)
    @ResponseBody
    Object AddUser (@RequestBody JSONObject form) throws Exception{
        User user = new User();
        JSONObject data = form.getJSONObject("data");
        user.setTel(data.getString("tel"));
        user.setRealName(data.getString("realName"));
        user.setGender(data.getString("gender"));
        user.setFix(data.getString("userFix"));
        user.setEmail(data.getString("email"));
        user.setAge(data.getInteger("age"));
        user.setRole(userService.getRole(data.getLong("roleId")));
        UserAuth auth = new UserAuth();
        auth.setPassword(data.getString("password"));
        auth.setAccount(data.getString("account"));
        auth.setUser(user);
        if (userAuthService.findByAccount(data.getString("account")).size() != 0) {
            throw new RETCode(103);
        }
        user.setAuth(auth);
        UserJob userJob = new UserJob();
        userJob.setManager(null);
        userJob.setFix(data.getString("jobFix"));
        userJob.setJob(data.getString("job"));
        userJob.setPost(data.getString("post"));
        userJob.setUser(user);
        user.setUserJob(userJob);
        Unit unit = unitService.findBy("name", data.getString("unit")).get(0);
        userJob.setUnit(unit);
        unit.addUser(userJob);
        userJobService.setOne(userJob);
        userService.setOne(user);
        unitService.setOne(unit);
        return user;
    }
    @RequestMapping(value = "/user/findBy", method = RequestMethod.POST)
    @ResponseBody
    Object findBy (@RequestBody JSONObject form) throws Exception {
        String type = form.getString("type");
        String unit = form.getString("unit");
        String value = form.getString("value");
        List<UserJob> list = userService.findBy(type, value, unit);
        return Json.parseJson(list);
    }
    @RequestMapping(value = "/user/getLoginRecords", method = RequestMethod.POST)
    @ResponseBody
    Object GetAllLoginRecords () throws Exception {
        List<LoginRecords> list = loginService.getAllRecords();
        return Json.parseJson(list);
    }
    @RequestMapping(value = "/user/findRecordsBy", method = RequestMethod.POST)
    @ResponseBody
    Object FindRecordsBy (@RequestBody JSONObject form) throws Exception {
        List<LoginRecords> list = loginService.findRecordsBy(form.getString("type"), form.getString("value"));
        return Json.parseJson(list);
    }
    /**
     * Material apis beneath
     * @param form
     * @return
     */
    @RequestMapping(value = "/material/applyCommit", method = RequestMethod.POST)
    @ResponseBody
    Object MaterialCommit (@RequestBody JSONObject form) throws Exception {
        Material m = JSONObject.toJavaObject(form, Material.class);
        m = materialAndGearService.setOne(m);
        return m;
    }
    @RequestMapping(value = "/material/getRecords", method = RequestMethod.POST)
    @ResponseBody
    Object GetMaterialRecords (HttpSession httpSession) throws Exception{
        preHandle(httpSession);
        List<Material> list = materialAndGearService.getAllMaterials();
        JSONArray js = (JSONArray)Json.parseJson(list);
        return js;
    }
    @RequestMapping(value = "/material/findBy", method = RequestMethod.POST)
    @ResponseBody
    Object FindMaterialBy (@RequestBody JSONObject form, HttpSession session) throws Exception {
        preHandle(session);
        String type = form.getString("type");
        String value = form.getString("value");
        List<Material> list = materialAndGearService.findMaterialsBy(type, value);
        return Json.parseJson(list);
    }
    @RequestMapping(value = "/material/getCount", method = RequestMethod.POST)
    @ResponseBody
    Object GetCount () throws Exception {
        return materialAndGearService.findMaterialsBy("result", "待审核").size();
    }
    @RequestMapping(value = "/material/censor", method = RequestMethod.POST)
    @ResponseBody
    Object Censor (@RequestBody JSONObject form) throws Exception {
        Material m = materialAndGearService.findMaterialOneById(form.getLong("id"));
        m.setResult(form.getString("result"));
        materialAndGearService.setOne(m);
        return m;
    }
    @RequestMapping(value = "/gear/applyCommit", method = RequestMethod.POST)
    @ResponseBody
    Object GearCommit (@RequestBody JSONObject form) throws Exception {
        Gear g = JSONObject.toJavaObject(form, Gear.class);
        return  materialAndGearService.setOne(g);
    }
    @RequestMapping(value = "/gear/getRecords", method = RequestMethod.POST)
    @ResponseBody
    Object GetGearRecords (HttpSession session) throws Exception {
        preHandle(session);
        List<Gear> list = materialAndGearService.getAllGears();
        JSONArray js = (JSONArray)Json.parseJson(list);
        return js;
    }
    @RequestMapping(value = "/gear/findBy", method = RequestMethod.POST)
    @ResponseBody
    Object FindGearBy (@RequestBody JSONObject form, HttpSession session) throws Exception {
        String type = form.getString("type");
        String value = form.getString("value");
        return Json.parseJson(materialAndGearService.findGearsBy(type, value));
    }


    @RequestMapping(value = "/gearStatus/applyCommit", method = RequestMethod.POST)
    @ResponseBody
    Object StatuCommit (@RequestBody JSONObject form) throws Exception {
        GearStatus gearStatus = JSONObject.toJavaObject(form, GearStatus.class);
        return materialAndGearService.setOne(gearStatus);
    }
    @RequestMapping(value = "/gearStatus/getRecords", method = RequestMethod.POST)
    @ResponseBody
    Object GetStatusRecords (HttpSession session) throws Exception {
        preHandle(session);
        List<GearStatus> list = materialAndGearService.getAllStatus();
        return Json.parseJson(list);
    }

    @RequestMapping(value = "/gearStatus/findBy", method = RequestMethod.POST)
    @ResponseBody
    Object FindStatusBy (@RequestBody JSONObject form, HttpSession session) throws Exception {
        preHandle(session);
        String type = form.getString("type");
        String value = form.getString("value");
        return Json.parseJson(materialAndGearService.findGearStatusBy(type, value));
    }
    /**
     * Unit APIs
     */
    @RequestMapping(value = "/unit/getResponsibles")
    @ResponseBody
    Object GetResponsibles (HttpSession session) throws Exception {
        preHandle(session);
        List<UserJob> list = userJobService.findAll();
        return Json.parseJson(list);
    }
    @RequestMapping(value = "/unit/getUnits")
    @ResponseBody
    Object GetUnits (HttpSession session) throws Exception {
        preHandle(session);
        List<Unit> list = unitService.findAll();
        JSONArray jsonArray = new JSONArray();
        for (Unit item : list) {
            JSONObject jsonObject = new JSONObject();
            jsonObject = (JSONObject)Json.parseJson(item);
            if (item.getResponsible() != null) {
                jsonObject.remove("responsible");
                jsonObject.put("responsible", Json.parseJson(item.getResponsible()));
            }
            jsonArray.add(jsonObject);
        }
        return jsonArray;
    }
    @RequestMapping(value = "/unit/addUnit")
    @ResponseBody
    Object AddUnit (HttpSession session, @RequestBody JSONObject form) throws Exception {
        preHandle(session);
        Unit unit = new Unit();
        Long responsible = form.getLong("responsibleId");
        if (responsible != 0) {
            UserJob userJob = userJobService.findOne(responsible);
            userJob.setManager(unit);
            unit.setResponsible(userJob);
        }
        Long superiorId = form.getLong("superiorId");
        if (superiorId != 0) {
            unit.setSuperior(unitService.findOne(superiorId));
            Unit superior = unitService.findOne(superiorId);
            superior.addJunior(unit);
            superior.setBased(false);
        }
        unit.setFix(form.getString("fix"));
        unit.setName(form.getString("name"));
        unit.setNumber(form.getString("number"));
        unit.setTel(form.getString("tel"));
        unit.setBased(true);
        unitService.setOne(unit);
        return unit;
    }
    @RequestMapping(value = "/unit/deleteUnit")
    @ResponseBody
    Object deleteUnit (@RequestBody JSONObject id, HttpSession session ) throws Exception {
        preHandle(session);
        Unit unit = unitService.findOne(id.getLong("id"));
        if (unit.getResponsible() != null) {
            UserJob userJob = userJobService.findOne(unit.getResponsible().getId());
            userJob.setManager(null);
        }
        if (unit.getSuperior() != null) {
            Unit superior = unitService.findOne(unit.getSuperior().getId());
            superior.removeJunior(unit);
            if (superior.getUnits().size() == 0) {
                superior.setBased(true);
            }
        }
        if (unit.getUnits().size() != 0) {
            for (Unit i : unit.getUnits()) {
                i.setSuperior(null);
            }
        }
        unitService.setOne(unit);
        unitService.deleteOne(id.getLong("id"));
        return null;
    }
    @RequestMapping(value = "/unit/updateUnit")
    @ResponseBody
    Object UpdateUnit (@RequestBody JSONObject form, HttpSession session) throws Exception {
        preHandle(session);
        Long id = form.getLong("id");
        Unit unit = unitService.findOne(id);
        unit.setTel(form.getString("tel"));
        unit.setNumber(form.getString("number"));
        unit.setName(form.getString("name"));
        unit.setFix(form.getString("fix"));
        Long responsible = form.getLong("responsibleId");
        UserJob userJob = unit.getResponsible();
        if (responsible != 0) {
            if (userJob != null) {
                userJob.setManager(null);
                userJobService.setOne(userJob);
            }
            UserJob newResponsible = userJobService.findOne(responsible);
            newResponsible.setManager(unit);
            unit.setResponsible(newResponsible);
        } else {
            if (userJob != null) {
                userJob.setManager(null);
                userJobService.setOne(userJob);
            }
            unit.setResponsible(null);
        }
        Long superior = form.getLong("superiorId");
        Unit s = unitService.findOne(superior);
        if (s != null){
            s.removeJunior(unit);
            if (s.getUnits().size() == 0) {
                s.setBased(true);
            }
            unitService.setOne(s);
        }
        if (superior != 0) {
            Unit newSuperior = unitService.findOne(superior);
            unit.setSuperior(newSuperior);
            newSuperior.addJunior(unit);
            newSuperior.setBased(false);
        } else {
            unit.setSuperior(null);
        }
        unit = unitService.setOne(unit);
        return Json.parseJson(unit);
    }
    @RequestMapping(value = "/unit/findUnitBy", method = RequestMethod.POST)
    @ResponseBody
    public Object findUnitBy (@RequestBody JSONObject form, HttpSession session) throws Exception {
        preHandle(session);
        List<Unit> unit = unitService.findBy(form.getString("type"), form.getString("value"));
        return Json.parseJson(unit);
    }
    @RequestMapping(value = "/unit/getJuniors", method = RequestMethod.POST)
    @ResponseBody
    public Object GetJuniors (@RequestBody JSONObject name) throws Exception {
        List<Unit> list = unitService.getJuniorByName(name.getString("name"));
        return Json.parseJson(list);
    }
    @RequestMapping(value = "/unit/getUsers", method = RequestMethod.POST)
    @ResponseBody
    public Object GetUsers(@RequestBody JSONObject name) throws Exception {
        List<UserJob> list = unitService.getUsers(name.getString("name"));
        return Json.parseJson(list);
    }
    /**
     * Exception handle
     * @param e
     * @return
     */
    @ResponseBody
    @ExceptionHandler(Exception.class)
    public RETCode handleException (Exception e) {
        e.printStackTrace();
        if (e instanceof RETCode) {
            return (RETCode)e;
        } else if (e instanceof SQLException) {
            return new RETCode(102);
        } else {
            return new RETCode(100);
        }
    }

    /**
     * parse List into Json
     * @param object
     */
//    Object parseJson (Object object) {
//        JSONArray jsonArray = new JSONArray();
//        JSONObject js;
//        if (object instanceof List) {
//            for (Object item : (List)object) {
//                    jsonArray.add(parseJson(item));
//            }
//            return jsonArray;
//        } else {
//            js = JSONObject.parseObject(JSONObject.toJSONString(object));
//            return js;
//        }
//    }

    /**
//     * parse Object into Json
//     * @param t
//     * @return
//     */
//    JSONObject parseJson (T t) {
//        JSONObject js = new JSONObject();
//        js = JSONObject.parseObject(JSONObject.toJSONString(t));
//        return js;
//    }
    /**
     * preHandle
     * login validate
     */
    String preHandle (HttpSession session) throws RETCode{
        String s = (String)session.getAttribute(session.getId());
        if (s == null) {
            throw new RETCode(101);
        } else {
            return s;
        }
    }
}
