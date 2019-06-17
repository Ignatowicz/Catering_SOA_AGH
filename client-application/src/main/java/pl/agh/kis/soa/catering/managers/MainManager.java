package pl.agh.kis.soa.catering.managers;

import lombok.Getter;
import lombok.Setter;
import pl.agh.kis.soa.catering.api.*;
import pl.agh.kis.soa.catering.model.*;
import pl.agh.kis.soa.catering.utils.OrderStatus;
import pl.agh.kis.soa.catering.utils.UserRole;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import javax.inject.Named;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;


@Getter
@Setter
@Named("MainManager")
@Singleton
@Startup
public class MainManager implements Serializable {

    private List<User> loggedUsers;

    @PostConstruct
    public void setupApplication() {
        this.loggedUsers = new ArrayList<User>();
    }

    public List<User> getLoggedUsers() {
        return loggedUsers;
    }

    public void setLoggedUsers(List<User> loggedUsers) {
        this.loggedUsers = loggedUsers;
    }

}
