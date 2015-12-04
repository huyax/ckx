package com.${project}.web.core.${module}.impl;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import com.${project}.web.core.${module}.${name}Service;
import com.${project}.lang.mybatis.Pager;
import com.${project}.web.persist.entity.${name};
import com.${project}.web.persist.mapper.${name}Mapper;

@Service
public class ${name}ServiceImpl implements ${name}Service
{
private @Autowired ${name}Mapper    ${nameLow}Mapper;

@Override
public void paginate(Pager pager)
{
pager.setResult(${nameLow}Mapper.paginate(pager.getParamsMap()));
pager.setTotalRecord(${nameLow}Mapper.paginateCount(pager.getParamsMap()));
}

@Override
@Transactional(propagation = Propagation.REQUIRED, rollbackFor = java.lang.Exception.class)
public boolean updateByPk(${name} ${nameLow})
{
return ${nameLow}Mapper.updateByPrimaryKey(${nameLow}) > 0 ? true : false;
}

@Override
@Transactional(propagation = Propagation.REQUIRED, rollbackFor = java.lang.Exception.class)
public boolean add(${name} ${nameLow})
{
return ${nameLow}Mapper.insert(${nameLow}) > 0 ? true : false;
}

@Override
@Transactional(propagation = Propagation.REQUIRED, rollbackFor = java.lang.Exception.class)
public int batchDelete(String ids)
{
List
<String> idsList = Arrays.asList(ids.split("\\|"));
    int i = 0;
    for(String id:idsList){
    i += ${nameLow}Mapper.deleteByPrimaryKey(Integer.valueOf(id));
    }
    return i;
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = java.lang.Exception.class)
    public int deleteById(Integer id)
    {
    return ${nameLow}Mapper.deleteByPrimaryKey(id);
    }

    }
