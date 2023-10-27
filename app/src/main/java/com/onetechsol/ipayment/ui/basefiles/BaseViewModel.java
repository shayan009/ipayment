package com.onetechsol.ipayment.ui.basefiles;

import androidx.lifecycle.ViewModel;

import com.onetechsol.ipayment.app.MainApp;
import com.onetechsol.ipayment.data.locale.pref.PrefManager;
import com.onetechsol.ipayment.service.repository.IModelRepository;

import javax.inject.Inject;

public abstract class BaseViewModel extends ViewModel {


    private IModelRepository iModelRepository;
    private PrefManager prefManager;


    public BaseViewModel() {
        super();
        Injector injector = new Injector();
        iModelRepository = injector.iModelRepository();
        prefManager = injector.prefManager();
    }


    public IModelRepository iModelRepository() {
        return iModelRepository;
    }

    public PrefManager prefManager() {
        return prefManager;
    }


    public static class Injector {

        @Inject
        IModelRepository iModelRepository;

        @Inject
        PrefManager prefManager;


        public Injector() {
            super();
            MainApp.appComponent().inject(this);
        }

        public IModelRepository iModelRepository() {
            return iModelRepository;
        }

        public PrefManager prefManager() {
            return prefManager;
        }
    }
}
