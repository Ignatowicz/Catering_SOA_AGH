package pl.agh.kis.soa.catering.implementation;

import pl.agh.kis.soa.catering.api.ISubscriptionRepository;
import pl.agh.kis.soa.catering.model.Subscription;

import javax.ejb.Remote;
import javax.ejb.Stateless;
import java.util.Date;
import java.util.List;


@Stateless
@Remote(ISubscriptionRepository.class)
public class SubscriptionRepository implements ISubscriptionRepository {
    @Override
    public void addSubscription(Subscription subscription) {

    }

    @Override
    public Subscription getSubscription(Long subscriptionId) {
        return null;
    }

    @Override
    public List<Subscription> getAllSubscriptions() {
        return null;
    }

    @Override
    public void updateSubscription(Subscription subscription) {

    }

    @Override
    public void deleteSubscription(Long subscriptionId) {

    }

    @Override
    public List<Subscription> getUserSubscriptions(Long userId) {
        return null;
    }

    @Override
    public List<Subscription> getUserSubscriptionDueToDate(Long userId, Date date) {
        return null;
    }
}
