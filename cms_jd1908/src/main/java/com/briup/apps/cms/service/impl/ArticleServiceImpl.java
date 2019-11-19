package com.briup.apps.cms.service.impl;

import com.briup.apps.cms.bean.Article;
import com.briup.apps.cms.bean.ArticleExample;
import com.briup.apps.cms.bean.extend.ArticleExtend;
import com.briup.apps.cms.config.CustomerException;
import com.briup.apps.cms.dao.ArticleMapper;
import com.briup.apps.cms.dao.extend.ArticleExtendMapper;
import com.briup.apps.cms.service.IArticleService;

import io.swagger.annotations.Example;

import org.springframework.stereotype.Service;

import javax.annotation.Resource;

import java.util.Date;
import java.util.List;

/**
 * @program: cms_jd1908
 * @description: 文章的业务实现类
 * @author: charles
 * @create: 2019-11-12 14:59
 **/
@Service
public class ArticleServiceImpl implements IArticleService {

    @Resource
    private ArticleMapper articleMapper;
    @Resource
    private ArticleExtendMapper articleExtendMapper;

    @Override
    public List<Article> findAll() {
        return articleMapper.selectByExample(new ArticleExample());
    }


    @Override
    public List<ArticleExtend> cascadeFindAll() {
        return articleExtendMapper.selectAll();
    }

    @Override
    public ArticleExtend findById(long id) {
        return articleExtendMapper.selectById(id);
    }
    
    
    @Override
    public void saveOrUpdate(Article article) {
    	if(article.getId()!=null) {
    		articleMapper.updateByPrimaryKey(article);
    	}else {
    		ArticleExample example = new ArticleExample();
    		example.createCriteria().andTitleEqualTo(article.getTitle());
    		List<Article> list = articleMapper.selectByExample(example );
    		if(list.size()>0) {
    			throw new CustomerException("文章标题不能重复");
    		}
    		article.setPublishTime(new Date().getTime());
    		article.setReadTimes(0l);
    		article.setThumpDown(0l);
    		article.setThumpUp(0l);
    		article.setStatus(ArticleExtend.STATUS_UNCHECK);
    		articleMapper.insert(article);
    	}
    	
    }


	@Override
	public void deleteById(long id) {
		ArticleExample example = new ArticleExample();
		example.createCriteria().andIdEqualTo(id);
		List<Article> list = articleMapper.selectByExample(example);
		if(list.size()==0) {
			throw new CustomerException("文章不存在");
		}
		articleMapper.deleteByPrimaryKey(id);
	}


	@Override
	public void batchDelete(long[] ids) {
		for (long id : ids) {
			this.deleteById(id);
		}
	}
}
