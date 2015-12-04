package com.ckx.web.core.lucene.impl;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field;
import org.apache.lucene.document.StringField;
import org.apache.lucene.document.TextField;
import org.apache.lucene.index.DirectoryReader;
import org.apache.lucene.index.IndexReader;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.index.Term;
import org.apache.lucene.queryparser.classic.QueryParser;
import org.apache.lucene.search.IndexSearcher;
import org.apache.lucene.search.Query;
import org.apache.lucene.search.ScoreDoc;
import org.apache.lucene.search.TopDocs;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.wltea.analyzer.lucene.IKAnalyzer;

import com.ckx.lang.util.DateUtil;
import com.ckx.web.core.bean.SearchBean;
import com.ckx.web.core.lucene.LuceneService;
import com.ckx.web.persist.entity.Movie;
import com.ckx.web.persist.mapper.MovieMapper;

@Service("luceneService")
public class LuceneServiceImpl implements LuceneService {
    private
    @Autowired(required = true)
    IndexWriter indexWriter;

    private
    @Autowired(required = true)
    MovieMapper movieMapper;

    private
    @Autowired(required = true)
    IndexSearcher indexSearcher;

    private
    @Autowired(required = true)
    IKAnalyzer analyzer;
    private Log log = LogFactory.getLog(getClass());

    @Override
    public boolean createIndex() {
        List<Movie> list = movieMapper.listAll();
        try {
            Document doc = null;
            for (Movie movie : list) {
                doc = new Document();
                Field id = new StringField("id", movie.getId() + "",
                        Field.Store.YES);
                Field name = new TextField("name", movie.getName() + "",
                        Field.Store.YES);
                Field pubDate = new StringField("pubDate",
                        DateUtil.formatDate(movie.getPubDate()),
                        Field.Store.YES);
                doc.add(id);
                doc.add(name);
                doc.add(pubDate);
                indexWriter.addDocument(doc);
            }

            indexWriter.close();
            log.debug("创建完成....");
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }

    }

    /**
     * 删除索引
     *
     * @param movie
     * @throws Exception
     */
    public void removeIndex(Movie movie) throws Exception {
        indexWriter.deleteDocuments(new Term("id", movie.getId() + ""));
        indexWriter.deleteDocuments(new Term("name", movie.getName()));
        indexWriter.deleteDocuments(new Term("pubDate", DateUtil
                .formatDate(movie.getPubDate())));
        indexWriter.close();
    }

    /**
     * 增加索引
     */
    public void addIndex(Movie movie) throws Exception {
        Document doc = null;
        doc = new Document();
        Field id = new StringField("id", movie.getId() + "", Field.Store.YES);
        Field name = new TextField("name", movie.getName() + "",
                Field.Store.YES);
        Field pubDate = new StringField("pubDate", DateUtil.formatDate(movie
                .getPubDate()), Field.Store.YES);
        doc.add(id);
        doc.add(name);
        doc.add(pubDate);
        indexWriter.addDocument(doc);
        indexWriter.close();
    }

    /**
     * 更新索引
     *
     * @throws Exception
     */
    public void updateIndex(Movie movie) throws Exception {

        Document doc = new Document();
        Field id = new StringField("id", movie.getId() + "", Field.Store.YES);
        Field name = new TextField("name", movie.getName() + "",
                Field.Store.YES);
        Field pubDate = new StringField("pubDate", DateUtil.formatDate(movie
                .getPubDate()), Field.Store.YES);
        doc.add(id);
        doc.add(name);
        doc.add(pubDate);
        indexWriter.updateDocument(new Term("name", movie.getName()), doc);
        indexWriter.close();

    }

    @Override
    public List<SearchBean> search(String q) throws Exception {
        QueryParser parser = new QueryParser("name", analyzer);
        Query query = parser.parse(q);

        TopDocs topDocs = indexSearcher.search(query, indexSearcher
                .getIndexReader().maxDoc());
        List<SearchBean> list = new ArrayList<SearchBean>();
        SearchBean bean = null;
        ScoreDoc[] scoreDocs = topDocs.scoreDocs;
        for (int i = 0; i < scoreDocs.length; i++) {
            int docId = scoreDocs[i].doc;
            Document doc = indexSearcher.doc(docId);
            bean = new SearchBean();
            bean.setId(doc.get("id"));
            bean.setName(doc.get("name"));
            bean.setPubDate(doc.get("pubDate"));
            list.add(bean);
        }
        return list;
    }

}
