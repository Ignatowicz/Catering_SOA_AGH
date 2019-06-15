package pl.agh.kis.soa.catering.api;

import pl.agh.kis.soa.catering.model.Subscription;

import java.util.Date;
import java.util.List;

public interface ISubscriptionRepository {

    void addSubscription(Subscription subscription);

    Subscription getSubscription(Long subscriptionId);

    List<Subscription> getAllSubscriptions();

    void updateSubscription(Subscription subscription);

    void deleteSubscription(Long subscriptionId);


    List<Subscription> getUserSubscriptions(Long userId);

    List<Subscription> getUserSubscriptionDueToDate(Long userId, Date date);

}
