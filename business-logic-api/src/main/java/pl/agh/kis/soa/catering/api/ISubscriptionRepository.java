package pl.agh.kis.soa.catering.api;

import pl.agh.kis.soa.catering.model.Subscription;

import java.util.List;


public interface ISubscriptionRepository {

    void addSubscription(Subscription subscription);

    Subscription getSubscription(Long subscriptionId);

    List<Object> getAllSubscriptions();

    void updateSubscription(Subscription subscription);

    void deleteSubscription(Long subscriptionId);


    List<Object> getUserSubscriptions(Long userId);

}
