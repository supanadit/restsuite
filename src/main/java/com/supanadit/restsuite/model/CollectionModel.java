package com.supanadit.restsuite.model;

import com.supanadit.restsuite.entity.CollectionBodyEntity;
import com.supanadit.restsuite.entity.CollectionEntity;
import com.supanadit.restsuite.entity.CollectionHeaderEntity;
import com.supanadit.restsuite.system.hibernate.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.ArrayList;
import java.util.List;

public class CollectionModel {
    private int id;
    private String title;
    private String url;
    private String method;
    private ArrayList<RequestHeadersFormInputModel> headers;
    private String bodyType;
    private ArrayList<RequestBodyFormInputModel> bodyForm;
    private String bodyRawType;
    private String bodyRawValue;

    public CollectionModel(String title, String url, String method, ArrayList<RequestHeadersFormInputModel> headers, String bodyType, ArrayList<RequestBodyFormInputModel> bodyForm, String bodyRawType, String bodyRawValue) {
        this.title = title;
        this.url = url;
        this.method = method;
        this.headers = headers;
        this.bodyType = bodyType;
        this.bodyForm = bodyForm;
        this.bodyRawType = bodyRawType;
        this.bodyRawValue = bodyRawValue;
    }

    public static CollectionModel fromApiModel(ApiModel apiModel) {
        int id = apiModel.getId();
        String title = apiModel.getTitle().getText();
        String url = apiModel.getUrl().getText();
        String method = apiModel.getRequestMethod().getName();
        ArrayList<RequestHeadersFormInputModel> headers = apiModel.getTabPanel().getRequestModel().getHeadersPanel().getHeadersFormPanel().getModel().getAllFormInput();
        String bodyType = apiModel.getTabPanel().getRequestModel().getBodyPanel().getRequestBodyType().getName();
        ArrayList<RequestBodyFormInputModel> bodyForm = apiModel.getTabPanel().getRequestModel().getBodyPanel().getBodyFormPanel().getModel().getAllFormInput();
        String bodyRawType = apiModel.getTabPanel().getRequestModel().getBodyPanel().getRequestBodyRawType().getName();
        String bodyRawValue = apiModel.getTabPanel().getRequestModel().getBodyPanel().getRequestBodyRawValue();

        CollectionModel collectionModel = new CollectionModel(title, url, method, headers, bodyType, bodyForm, bodyRawType, bodyRawValue);
        collectionModel.id = id;

        return collectionModel;
    }

    public static CollectionModel fromStorage(int id) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        CollectionEntity collectionEntity = session.get(CollectionEntity.class, id);

        List<CollectionHeaderEntity> queryHeaderList = session.createQuery("from CollectionHeaderEntity item where item.collectionID = :id", CollectionHeaderEntity.class).list();
        List<CollectionBodyEntity> queryBodyList = session.createQuery("from CollectionBodyEntity item where item.collectionID = :id", CollectionBodyEntity.class).list();

        ArrayList<RequestHeadersFormInputModel> headers = new ArrayList<>();
        ArrayList<RequestBodyFormInputModel> bodyForm = new ArrayList<>();

        for (CollectionHeaderEntity header : queryHeaderList) {
            headers.add(new RequestHeadersFormInputModel(header.getKey(), header.getValue()));
        }

        for (CollectionBodyEntity body : queryBodyList) {
            bodyForm.add(new RequestBodyFormInputModel(body.getType(), body.getKey(), body.getValue()));
        }

        return new CollectionModel(collectionEntity.getTitle(), collectionEntity.getUrl(), collectionEntity.getMethod(), headers, collectionEntity.getBodyType(), bodyForm, collectionEntity.getBodyRawType(), collectionEntity.getBodyRawValue());
    }

    public CollectionModel save() {
        CollectionEntity collection = getCollectionEntity();
        if (id != 0) {
            collection.setId(id);
        }
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            // start a transaction
            transaction = session.beginTransaction();
            // save the project objects
            session.saveOrUpdate(collection);
            // Set ID
            id = collection.getId();
            System.out.println("ID ".concat(String.valueOf(id)));
            // Save Headers
            for (RequestHeadersFormInputModel header : headers) {
                session.save(new CollectionHeaderEntity(id, header.getKey(), header.getValue()));
            }
            // Save Body
            for (RequestBodyFormInputModel body : bodyForm) {
                session.save(new CollectionBodyEntity(id, body.getType(), body.getKey(), body.getValue()));
            }
            // commit transaction
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
        return this;
    }

    public CollectionEntity getCollectionEntity() {
        return new CollectionEntity(title, url, method, bodyType, bodyRawType, bodyRawValue);
    }

    public int getId() {
        return id;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getTitle() {
        return title;
    }

    public String getUrl() {
        return url;
    }

    public String getMethod() {
        return method;
    }

    public ArrayList<RequestHeadersFormInputModel> getHeaders() {
        return headers;
    }

    public String getBodyType() {
        return bodyType;
    }

    public ArrayList<RequestBodyFormInputModel> getBodyForm() {
        return bodyForm;
    }

    public String getBodyRawType() {
        return bodyRawType;
    }

    public String getBodyRawValue() {
        return bodyRawValue;
    }

    @Override
    public String toString() {
        String stringCollection = "Name : ".concat((getTitle() != null) ? getTitle() : "null").concat("\n")
                .concat("URL : ").concat(getUrl()).concat("\n")
                .concat("Method : ").concat(getMethod()).concat("\n")
                .concat("Header : ").concat("\n");

        for (RequestHeadersFormInputModel header : getHeaders()) {
            stringCollection = stringCollection.concat("-").concat(" ").concat(header.getKey()).concat(" = ").concat(header.getValue()).concat("\n");
        }
        stringCollection = stringCollection.concat("Body Type : ").concat(getBodyType()).concat("\n")
                .concat("Body Form : ").concat("\n");
        for (RequestBodyFormInputModel form : getBodyForm()) {
            stringCollection = stringCollection.concat("-").concat(" ").concat("(").concat(form.getType()).concat(")").concat(" ").concat(form.getKey()).concat(" = ").concat(form.getValue()).concat("\n");
        }
        stringCollection = stringCollection.concat("Body Raw Type : ").concat(getBodyRawType()).concat("\n")
                .concat("Body Raw Value : ").concat(getBodyRawValue()).concat("\n");
        return stringCollection;
    }
}
