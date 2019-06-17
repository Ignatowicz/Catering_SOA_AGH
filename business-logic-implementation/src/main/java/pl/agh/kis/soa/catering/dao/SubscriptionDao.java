package pl.agh.kis.soa.catering.dao;

import pl.agh.kis.soa.catering.model.Subscription;


public class SubscriptionDao extends AbstractModelDao<Subscription> {

    private static SubscriptionDao instance;

    private SubscriptionDao() {
        super(Subscription.class);
    }

    public static SubscriptionDao getInstance() {
        if (instance == null) {
            instance = new SubscriptionDao();
        }
        return instance;
    }

}
