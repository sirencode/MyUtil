package com.util.yongheshen.myutil.mvp.presenter;

import com.util.yongheshen.myutil.mvp.model.MvpBean;
import com.util.yongheshen.myutil.mvp.model.MvpModel;
import com.util.yongheshen.myutil.mvp.view.MVPView;

/**
 * Created by Diablo on 16/4/28.
 * View和Model的桥梁，它从Model层检索数据后，返回给View层
 * m->p->v M通过P的接口数据传给P  P通过V的接口改变V
 * M持有P的接口来传输数据  P持有V的接口来改变V
 */
public class MvpPresenter implements Presenter<MVPView>,IMvpPresenter{

    private MVPView mMVPView;
    private MvpModel mMvpModel;

    public MvpPresenter(MVPView mvpView){
        attachView(mvpView);
        mMvpModel = new MvpModel(this);
    }

    @Override
    public void loadDataSuccess(MvpBean mvpBean) {
        mMVPView.showData(mvpBean);
        mMVPView.hideProgress();
    }

    @Override
    public void loadDataFailure() {
        mMVPView.hideProgress();
    }

    public void loadData(){
        mMVPView.showProgress();
        mMvpModel.loadData();
    }
    @Override
    public void attachView(MVPView view) {
        this.mMVPView = view;
    }

    @Override
    public void detachView() {
        this.mMVPView = null;
    }
}
