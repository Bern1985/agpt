package com.ancs.agpt.system.service.impl;



import static com.google.common.collect.Lists.newArrayList;

import java.lang.reflect.ParameterizedType;
import java.time.LocalDateTime;
import java.util.Collection;
import java.util.List;

import javax.management.RuntimeErrorException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.ancs.agpt.mybatis.plugin.Page;
import com.ancs.agpt.system.entity.DomainRole;
import com.ancs.agpt.system.entity.RestUrl;
import com.ancs.agpt.system.entity.SuperEntity;
import com.ancs.agpt.system.mapper.BaseMapper;
import com.ancs.agpt.system.service.BaseService;
import com.ancs.agpt.system.toolkit.SqlHelper;
import com.google.common.collect.Lists;
import static com.google.common.collect.Lists.*;

public abstract class BaseServiceImpl<M extends BaseMapper<T>, T extends SuperEntity> implements BaseService<T>  {
	
	 @Autowired
	 protected M baseMapper;
	/**
     * <p>
     * 判断数据库操作是否成功
     * </p>
     * <p>
     * 注意！！ 该方法为 Integer 判断，不可传入 int 基本类型
     * </p>
     *
     * @param result 数据库操作返回影响条数
     * @return boolean
     */
    protected static boolean retBool(Integer result) {
        return SqlHelper.retBool(result);
    }

    @Transactional(propagation=Propagation.REQUIRED,isolation = Isolation.READ_COMMITTED,rollbackFor = Exception.class,timeout=60)
	public void insertBatch(Collection<T> entityArray) {
    	List<T> data = newArrayList(entityArray);
		int batchSize = 500;//每次提交500条
		int loop = (int) Math.ceil(data.size() / (double) batchSize);
		List<List<T>> subList=Lists.partition(data,loop);  
		subList.stream().forEach(list -> baseMapper.insertBatch(list));
	}
    
    @Transactional(propagation=Propagation.REQUIRED,isolation = Isolation.READ_COMMITTED,rollbackFor = Exception.class,timeout=60)
   	public boolean insert(T entity) {
   		// TODO Auto-generated method stub
   		return retBool(baseMapper.insert(entity));
   	}


    @Transactional(propagation=Propagation.REQUIRED,isolation = Isolation.READ_COMMITTED,rollbackFor = Exception.class,timeout=60)
	public boolean update(T entity) {
		// TODO Auto-generated method stub
		return retBool(baseMapper.update(entity));
	}

    @Transactional(readOnly = true, propagation=Propagation.SUPPORTS)
	public T selectById(Long id) {
		// TODO Auto-generated method stub
    	return baseMapper.selectById(id);
	}

    @Transactional(propagation=Propagation.REQUIRED,isolation = Isolation.READ_COMMITTED,rollbackFor = Exception.class,timeout=60)
	public boolean setStatus(T paramT) {
		// TODO Auto-generated method stub
		return retBool(baseMapper.setStatus(paramT));
	}


	@Transactional(readOnly = true, propagation=Propagation.SUPPORTS)
	public Page<T> selectPage(Page<T> page) {
		// TODO Auto-generated method stub
		if(null==page) {
			page = new Page<T>(1,Integer.MAX_VALUE);
		}
		page.setRecords(baseMapper.selectPage(page));
		return page;
	}
	
	@Transactional(propagation=Propagation.REQUIRED,isolation = Isolation.READ_COMMITTED,rollbackFor = Exception.class,timeout=60)
	public boolean deleteById(Long id) {
		// TODO Auto-generated method stub
		 ParameterizedType type = (ParameterizedType) this.getClass().getGenericSuperclass();
		 Class clazz = (Class) type.getActualTypeArguments()[1];
		 T model = null;
		try {
			model = (T) clazz.newInstance();
			model.setId(id);
			model.setTs(LocalDateTime.now());
			model.setDr(1);
		} catch (InstantiationException | IllegalAccessException e) {
			// TODO Auto-generated catch block
			throw new RuntimeException(e.getMessage());
		}
    	return retBool(baseMapper.update(model));
	}
}
