package com.crazy.controller;


import com.crazy.mapper.ItemMapper;
import com.crazy.model.Item;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by SHIKUN on 2016/9/11.
 */
@RestController
@RequestMapping("/items")
@Transactional
public class ItemController_refine {

    @Autowired
    private ItemMapper itemMapper;

    @RequestMapping(method = RequestMethod.GET)
    public List<Item> getAll() {
        return itemMapper.findall();
    }

    @RequestMapping(method = RequestMethod.POST)
    public int addItem(@RequestBody Item item) {

        return itemMapper.addItem(item.isChecked(), item.getDescription());
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public int deleteItem(@PathVariable Integer id) {
        return itemMapper.deleteItem(id);
    }

    @RequestMapping(method = RequestMethod.PUT)
    public int changeInfo(@RequestBody Item item) {
        return itemMapper.update(item.getId(), item.isChecked());

    }

}



