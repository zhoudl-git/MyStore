package com.taotao.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.taotao.commons.pojo.EasyUITreeNode;
import com.taotao.mapper.TbItemCatMapper;
import com.taotao.pojo.TbItemCat;
import com.taotao.pojo.TbItemCatExample;
import com.taotao.pojo.TbItemCatExample.Criteria;
import com.taotao.service.ItemCatService;
@Service
public class ItemCatServiceImpl implements ItemCatService{
	
	@Autowired
	TbItemCatMapper itemCatMapper ;
	
	@Override
	public List<EasyUITreeNode> getItemCatList(long parentId) {
		// 根据parentId查询分类列表
		TbItemCatExample example = new TbItemCatExample();
		//设置查询条件
		Criteria criteria = example.createCriteria();
		criteria.andParentIdEqualTo(parentId);
		//执行查询
		List<TbItemCat> list = itemCatMapper.selectByExample(example);
		// 转换成EasyUITreeNode节点
		List<EasyUITreeNode> resultList = new ArrayList<EasyUITreeNode>();
		if(null != list && list.size() > 0){
			for(TbItemCat itemCat : list){
				EasyUITreeNode treeNode = new EasyUITreeNode();
				treeNode.setId(itemCat.getId());
				treeNode.setText(itemCat.getName());
				treeNode.setState(itemCat.getIsParent()?"closed":"open");
				//添加到列表中
				resultList.add(treeNode);
			}
		}
		
		return resultList;
	}

}
