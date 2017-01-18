package com.taotao.service;



import com.taotao.commons.pojo.EasyUIDataGridResult;
import com.taotao.commons.pojo.TaotaoResult;
import com.taotao.pojo.TbItem;

public interface ItemService {
	TbItem getItemById(long itemId);
	EasyUIDataGridResult getItemList (int page, int rows);
	TaotaoResult createItem(TbItem item, String desc);
}
