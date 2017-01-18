package com.taotao.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.taotao.commons.pojo.EasyUIDataGridResult;
import com.taotao.commons.pojo.TaotaoResult;
import com.taotao.commons.utils.IDUtils;
import com.taotao.mapper.TbItemDescMapper;
import com.taotao.mapper.TbItemMapper;
import com.taotao.pojo.TbItem;
import com.taotao.pojo.TbItemDesc;
import com.taotao.pojo.TbItemExample;
import com.taotao.pojo.TbItemExample.Criteria;
import com.taotao.service.ItemService;

@Service
public class ItemServiceImpl implements ItemService {
	
	@Autowired
	private TbItemMapper itemMapper;
	
	@Autowired
	TbItemDescMapper itemDescMapper;
	
	@Override
	public TbItem getItemById(long itemId) {
		//TbItem item = itemMapper.selectByPrimaryKey(itemId);
		TbItemExample example = new TbItemExample();
		Criteria criteria = example.createCriteria();
		criteria.andIdEqualTo(itemId);
		List<TbItem> itemList = itemMapper.selectByExample(example);
		TbItem item = null;
		if(null != itemList && itemList.size() > 0){
			item = itemList.get(0);
		}
		return item;
	}

	@Override
	public EasyUIDataGridResult getItemList(int page, int rows) {
		// 执行查询
		PageHelper.startPage(page, rows);
		TbItemExample example = new TbItemExample();
		List<TbItem> list = itemMapper.selectByExample(example);
		// 取分页信息
		PageInfo<TbItem> pageInfo = new PageInfo<>(list);
		// 返回处理结果
		EasyUIDataGridResult result = new EasyUIDataGridResult();
		result.setRows(list);
		result.setTotal(pageInfo.getTotal());
		return result;
	}

	@Override
	public TaotaoResult createItem(TbItem item, String desc) {
		// 生成商品id
		long itemId = IDUtils.genItemId();
		// 补全TbItem属性
		item.setId(itemId);
		// '商品状态，1-正常，2-下架，3-删除'
		item.setStatus((byte) 1);
		// 创建时间和更新时间
		Date date = new Date();
		item.setCreated(date);
		item.setUpdated(date);
		// 插入商品表
		itemMapper.insert(item);
		// 商品描述
		TbItemDesc itemDesc = new TbItemDesc();
		itemDesc.setItemId(itemId);
		itemDesc.setItemDesc(desc);
		itemDesc.setCreated(date);
		itemDesc.setUpdated(date);
		// 插入商品描述数据
		itemDescMapper.insert(itemDesc);

		return TaotaoResult.ok();

	}


}
