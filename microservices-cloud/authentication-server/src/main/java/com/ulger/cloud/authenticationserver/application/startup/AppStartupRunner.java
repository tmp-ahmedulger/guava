package com.ulger.cloud.authenticationserver.application.startup;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

@Component
public class AppStartupRunner implements ApplicationRunner {

    private final ApplicationRunner roleDataLoaderListener;

    public AppStartupRunner(@Qualifier("roleDataLoaderListener") ApplicationRunner roleDataLoaderListener) {
        this.roleDataLoaderListener = roleDataLoaderListener;
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {
        roleDataLoaderListener.run(args);
    }
}