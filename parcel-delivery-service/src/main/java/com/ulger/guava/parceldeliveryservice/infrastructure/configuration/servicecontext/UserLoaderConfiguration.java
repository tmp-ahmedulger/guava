package com.ulger.guava.parceldeliveryservice.infrastructure.configuration.servicecontext;

import com.ulger.guava.parceldeliveryservice.api.user.UserLoader;
import com.ulger.guava.parceldeliveryservice.api.user.UserLoaderProvider;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class UserLoaderConfiguration {

    private final UserLoader securityContextUserLoader;
    private final UserLoader remoteUserLoader;

    public UserLoaderConfiguration(
            @Qualifier("securityContextUserLoader") UserLoader securityContextUserLoader,
            @Qualifier("remoteUserLoader") UserLoader remoteUserLoader) {

        this.securityContextUserLoader = securityContextUserLoader;
        this.remoteUserLoader = remoteUserLoader;
    }

    @Bean
    @Qualifier("userLoaderProvider")
    public UserLoader userLoaderProvider() {
        UserLoaderProvider userLoaderProvider = new UserLoaderProvider();
        userLoaderProvider.addUserLoaderProvider(securityContextUserLoader);
        userLoaderProvider.addUserLoaderProvider(remoteUserLoader);

        return userLoaderProvider;
    }
}