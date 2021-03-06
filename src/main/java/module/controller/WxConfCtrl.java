/**
 * Copyright (c) 2015-2016, Javen Zhou  (javenlife@126.com).
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 */

package module.controller;

import java.util.List;

import org.apache.shiro.authz.annotation.RequiresAuthentication;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.util.ByteSource;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.jfinal.aop.Before;
import com.jfinal.core.Controller;
import com.jfinal.plugin.activerecord.Page;
import com.jfinal.plugin.activerecord.Record;
import com.jfinal.plugin.activerecord.tx.Tx;

import frame.plugin.collerbind.Coller;
import frame.plugin.easyui.DataGrid;
import frame.plugin.shiro.ShiroKit;
import module.entity.Message;
import module.model.ConfModel;
import module.model.ShiroModel;

/**
 * @author hadong
 */
@Coller(value = { "/wx/config" }, path = "config")
public class WxConfCtrl extends Controller {
	@RequiresAuthentication
	public void index() {
		setAttr("role", ShiroModel.dao.findRole(ShiroKit.who()).getStr("role_desc"));
		render("index.htm");
	}

	@RequiresAuthentication
	public void menu_get() {
		List<Record> menus = ShiroModel.dao.findUrls(ShiroKit.who());
		renderJson(menus);
	}

	@RequiresAuthentication
	public void role() {
		render("role.htm");
	}

	@RequiresAuthentication
	public void role_get() {
		Integer pageNumber = getParaToInt("page", 1);
		Integer pageSize = getParaToInt("rows", 10);
		Integer pagination = getParaToInt("pagination", 0);
		if (pagination == 0) {
			List<Record> roleList = ShiroModel.dao.findAllRole();
			renderJson(roleList);
		} else {
			Page<Record> rolePage = ShiroModel.dao.findAllRolePage(pageNumber, pageSize);
			renderJson(new DataGrid(String.valueOf(rolePage.getTotalRow()), rolePage.getList()));
		}
	}

	@RequiresAuthentication
	@Before(Tx.class)
	public void role_save() {
		JSONArray insertedJson = JSON.parseArray(getPara("inserted"));
		JSONArray updatedJson = JSON.parseArray(getPara("updated"));
		JSONArray deletedJson = JSON.parseArray(getPara("deleted"));
		if (insertedJson.size() > 0) {
			ShiroModel.dao.insertRole(insertedJson);
		}
		if (updatedJson.size() > 0) {
			ShiroModel.dao.updateRole(updatedJson);
		}
		if (deletedJson.size() > 0) {
			ShiroModel.dao.deleteRole(deletedJson);
		}
		renderJson(new Message("200", "success", "保存成功！"));
	}

	@RequiresAuthentication
	public void permission() {
		render("permission.htm");
	}

	@RequiresAuthentication
	public void permission_get() {
		Integer pageNumber = getParaToInt("page", 1);
		Integer pageSize = getParaToInt("rows", 10);
		Integer pagination = getParaToInt("pagination", 0);
		if (pagination == 0) {
			List<Record> permissionList = ShiroModel.dao.findAllPermission();
			renderJson(permissionList);
		} else {
			Page<Record> permissionPage = ShiroModel.dao.findAllPermissionPage(pageNumber, pageSize);
			renderJson(new DataGrid(String.valueOf(permissionPage.getTotalRow()), permissionPage.getList()));
		}
	}

	@RequiresAuthentication
	@Before(Tx.class)
	public void permission_save() {
		JSONArray insertedJson = JSON.parseArray(getPara("inserted"));
		JSONArray updatedJson = JSON.parseArray(getPara("updated"));
		JSONArray deletedJson = JSON.parseArray(getPara("deleted"));
		if (insertedJson.size() > 0) {
			ShiroModel.dao.insertPermission(insertedJson);
		}
		if (updatedJson.size() > 0) {
			ShiroModel.dao.updatePermission(updatedJson);
		}
		if (deletedJson.size() > 0) {
			ShiroModel.dao.deletePermission(deletedJson);
		}
		renderJson(new Message("200", "success", "保存成功！"));
	}

	@RequiresAuthentication
	public void url() {
		render("url.htm");
	}

	@RequiresAuthentication
	public void url_get() {
		Integer pageNumber = getParaToInt("page", 1);
		Integer pageSize = getParaToInt("rows", 10);
		Integer pagination = getParaToInt("pagination", 0);
		if (pagination == 0) {
			List<Record> urlsList = ShiroModel.dao.findAllUrls();
			renderJson(urlsList);
		} else {
			Page<Record> urlsPage = ShiroModel.dao.findAllUrlsPage(pageNumber, pageSize);
			renderJson(new DataGrid(String.valueOf(urlsPage.getTotalRow()), urlsPage.getList()));
		}
	}

	@RequiresAuthentication
	@Before(Tx.class)
	public void url_save() {
		JSONArray insertedJson = JSON.parseArray(getPara("inserted"));
		JSONArray updatedJson = JSON.parseArray(getPara("updated"));
		JSONArray deletedJson = JSON.parseArray(getPara("deleted"));
		if (insertedJson.size() > 0) {
			ShiroModel.dao.insertUrls(insertedJson);
		}
		if (updatedJson.size() > 0) {
			ShiroModel.dao.updateUrls(updatedJson);
		}
		if (deletedJson.size() > 0) {
			ShiroModel.dao.deleteUrls(deletedJson);
		}
		renderJson(new Message("200", "success", "保存成功！"));
	}

	@RequiresAuthentication
	public void urltype() {
		render("url-type.htm");
	}

	@RequiresAuthentication
	public void urltype_get() {
		Integer pageNumber = getParaToInt("page", 1);
		Integer pageSize = getParaToInt("rows", 10);
		Integer pagination = getParaToInt("pagination", 0);
		if (pagination == 0) {
			List<Record> urlsTypeList = ShiroModel.dao.findAllUrlsType();
			renderJson(urlsTypeList);
		} else {
			Page<Record> urlsTypePage = ShiroModel.dao.findAllUrlsTypePage(pageNumber, pageSize);
			renderJson(new DataGrid(String.valueOf(urlsTypePage.getTotalRow()), urlsTypePage.getList()));
		}

	}

	@RequiresAuthentication
	@Before(Tx.class)
	public void urltype_save() {
		JSONArray insertedJson = JSON.parseArray(getPara("inserted"));
		JSONArray updatedJson = JSON.parseArray(getPara("updated"));
		JSONArray deletedJson = JSON.parseArray(getPara("deleted"));
		if (insertedJson.size() > 0) {
			ShiroModel.dao.insertUrlsType(insertedJson);
		}
		if (updatedJson.size() > 0) {
			ShiroModel.dao.updateUrlsType(updatedJson);
		}
		if (deletedJson.size() > 0) {
			ShiroModel.dao.deleteUrlsType(deletedJson);
		}
		renderJson(new Message("200", "success", "保存成功！"));
	}

	@RequiresAuthentication
	public void user() {
		render("user.htm");
	}

	@RequiresAuthentication
	public void user_get() {
		Integer pageNumber = getParaToInt("page", 1);
		Integer pageSize = getParaToInt("rows", 10);
		Integer pagination = getParaToInt("pagination", 0);
		if (pagination == 0) {
			List<Record> userList = ShiroModel.dao.findAllUser();
			renderJson(userList);
		} else {
			Page<Record> userPage = ShiroModel.dao.findAllUserPage(pageNumber, pageSize);
			renderJson(new DataGrid(String.valueOf(userPage.getTotalRow()), userPage.getList()));
		}

	}

	@RequiresAuthentication
	@Before(Tx.class)
	public void user_save() {
		JSONArray insertedJson = JSON.parseArray(getPara("inserted"));
		JSONArray updatedJson = JSON.parseArray(getPara("updated"));
		JSONArray deletedJson = JSON.parseArray(getPara("deleted"));
		if (insertedJson.size() > 0) {
			ShiroModel.dao.insertUser(insertedJson);
		}
		if (updatedJson.size() > 0) {
			ShiroModel.dao.updateUser(updatedJson);
		}
		if (deletedJson.size() > 0) {
			ShiroModel.dao.deleteUser(deletedJson);
		}
		renderJson(new Message("200", "success", "保存成功！"));
	}

	@RequiresAuthentication
	public void password_create() {
		String password = getPara("password", "");
		String salt = getPara("salt", "");
		String newPassword = new SimpleHash("md5", password, ByteSource.Util.bytes(salt), 2).toHex();
		renderText(newPassword);
	}

	@RequiresAuthentication
	public void conf() {
		render("conf.htm");
	}

	@RequiresAuthentication
	public void conf_get() {
		Integer pageNumber = getParaToInt("page", 1);
		Integer pageSize = getParaToInt("rows", 10);
		Page<Record> sysConfigs = ConfModel.dao.findAllConfigPage(pageNumber, pageSize);
		renderJson(new DataGrid(String.valueOf(sysConfigs.getTotalRow()), sysConfigs.getList()));
	}

	@RequiresAuthentication
	@Before(Tx.class)
	public void conf_save() {
		JSONArray insertedJson = JSON.parseArray(getPara("inserted"));
		JSONArray updatedJson = JSON.parseArray(getPara("updated"));
		JSONArray deletedJson = JSON.parseArray(getPara("deleted"));
		if (insertedJson.size() > 0) {
			ConfModel.dao.insertConfig(insertedJson);
		}
		if (updatedJson.size() > 0) {
			ConfModel.dao.updateConfig(updatedJson);
		}
		if (deletedJson.size() > 0) {
			ConfModel.dao.deleteConfig(deletedJson);
		}
		renderJson(new Message("200", "success", "保存成功！"));
	}

	@RequiresAuthentication
	public void conftype() {
		render("conf-type.htm");
	}

	@RequiresAuthentication
	public void conftype_get() {
		Integer pageNumber = getParaToInt("page", 1);
		Integer pageSize = getParaToInt("rows", 10);
		Integer pagination = getParaToInt("pagination", 0);
		if (pagination == 0) {
			List<Record> configTypesList = ConfModel.dao.findAllConfigType();
			renderJson(configTypesList);
		} else {
			Page<Record> configTypesPage = ConfModel.dao.findAllConfigTypePage(pageNumber, pageSize);
			renderJson(new DataGrid(String.valueOf(configTypesPage.getTotalRow()), configTypesPage.getList()));
		}
	}

	@RequiresAuthentication
	@Before(Tx.class)
	public void conftype_save() {
		JSONArray insertedJson = JSON.parseArray(getPara("inserted"));
		JSONArray updatedJson = JSON.parseArray(getPara("updated"));
		JSONArray deletedJson = JSON.parseArray(getPara("deleted"));
		if (insertedJson.size() > 0) {
			ConfModel.dao.insertConfigType(insertedJson);
		}
		if (updatedJson.size() > 0) {
			ConfModel.dao.updateConfigType(updatedJson);
		}
		if (deletedJson.size() > 0) {
			ConfModel.dao.deleteConfigType(deletedJson);
		}
		renderJson(new Message("200", "success", "保存成功！"));
	}

	@RequiresAuthentication
	public void qacontent() {
		render("qa-content.htm");
	}

	@RequiresAuthentication
	public void qacontent_get() {
		Integer pageNumber = getParaToInt("page", 1);
		Integer pageSize = getParaToInt("rows", 10);
		Integer pagination = getParaToInt("pagination", 0);
		if (pagination == 0) {
			List<Record> qasList = ConfModel.dao.findAllQAContent();
			renderJson(qasList);
		} else {
			Page<Record> qasPage = ConfModel.dao.findAllQAContentPage(pageNumber, pageSize);
			renderJson(new DataGrid(String.valueOf(qasPage.getTotalRow()), qasPage.getList()));
		}
	}

	@RequiresAuthentication
	@Before(Tx.class)
	public void qacontent_save() {
		JSONArray insertedJson = JSON.parseArray(getPara("inserted"));
		JSONArray updatedJson = JSON.parseArray(getPara("updated"));
		JSONArray deletedJson = JSON.parseArray(getPara("deleted"));
		if (insertedJson.size() > 0) {
			ConfModel.dao.insertQAContent(insertedJson);
		}
		if (updatedJson.size() > 0) {
			ConfModel.dao.updateQAContent(updatedJson);
		}
		if (deletedJson.size() > 0) {
			ConfModel.dao.deleteQAContent(deletedJson);
		}
		renderJson(new Message("200", "success", "保存成功！"));
	}

	@RequiresAuthentication
	public void qatype() {
		render("qa-type.htm");
	}

	@RequiresAuthentication
	public void qatype_get() {
		Integer pageNumber = getParaToInt("page", 1);
		Integer pageSize = getParaToInt("rows", 10);
		Integer pagination = getParaToInt("pagination", 0);
		if (pagination == 0) {
			List<Record> configTypesList = ConfModel.dao.findAllQAType();
			renderJson(configTypesList);
		} else {
			Page<Record> QATypesPage = ConfModel.dao.findAllQATypePage(pageNumber, pageSize);
			renderJson(new DataGrid(String.valueOf(QATypesPage.getTotalRow()), QATypesPage.getList()));
		}
	}

	@RequiresAuthentication
	@Before(Tx.class)
	public void qatype_save() {
		JSONArray insertedJson = JSON.parseArray(getPara("inserted"));
		JSONArray updatedJson = JSON.parseArray(getPara("updated"));
		JSONArray deletedJson = JSON.parseArray(getPara("deleted"));
		if (insertedJson.size() > 0) {
			ConfModel.dao.insertQAType(insertedJson);
		}
		if (updatedJson.size() > 0) {
			ConfModel.dao.updateQAType(updatedJson);
		}
		if (deletedJson.size() > 0) {
			ConfModel.dao.deleteQAType(deletedJson);
		}
		renderJson(new Message("200", "success", "保存成功！"));
	}

	@RequiresAuthentication
	public void wxuser() {
		render("wx-user.htm");
	}

	@RequiresAuthentication
	public void wxuser_get() {
		Integer pageNumber = getParaToInt("page", 1);
		Integer pageSize = getParaToInt("rows", 10);
		Integer pagination = getParaToInt("pagination", 0);
		if (pagination == 0) {
			List<Record> wuList = ConfModel.dao.findAllWxUser();
			renderJson(wuList);
		} else {
			Page<Record> wuPage = ConfModel.dao.findAllWxUserPage(pageNumber, pageSize);
			renderJson(new DataGrid(String.valueOf(wuPage.getTotalRow()), wuPage.getList()));
		}
	}
	
	@RequiresAuthentication
	public void wxquestion() {
		render("wx-user-qa.htm");
	}
	
	@RequiresAuthentication
	public void wxuser_qa_get() {
		Integer pageNumber = getParaToInt("page", 1);
		Integer pageSize = getParaToInt("rows", 10);
		Integer pagination = getParaToInt("pagination", 0);
		if (pagination == 0) {
			List<Record> wuList = ConfModel.dao.findAllWxUserQA();
			renderJson(wuList);
		} else {
			Page<Record> wuPage = ConfModel.dao.findAllWxUserPageQA(pageNumber, pageSize);
			renderJson(new DataGrid(String.valueOf(wuPage.getTotalRow()), wuPage.getList()));
		}
	}
}
