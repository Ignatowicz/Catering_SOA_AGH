package pl.agh.kis.soa.catering.implementation;

import lombok.Getter;
import lombok.Setter;
import pl.agh.kis.soa.catering.api.ISubscriptionRepository;
import pl.agh.kis.soa.catering.dao.SubscriptionDao;
import pl.agh.kis.soa.catering.model.Subscription;

import javax.ejb.Remote;
import javax.ejb.Stateless;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


@Getter
@Setter
@Stateless
@Remote(ISubscriptionRepository.class)
public class SubscriptionRepository implements ISubscriptionRepository {

    @Override
    public void addSubscription(Subscription subscription) {
        SubscriptionDao.getInstance().addItem(subscription);
    }

    @Override
    public Subscription getSubscription(Long subscriptionId) {
        return SubscriptionDao.getInstance().getItem(subscriptionId);
    }

    @Override
    public List<Object> getAllSubscriptions() {
        return new ArrayList<>(Arrays.asList(SubscriptionDao.getInstance().getItems().toArray()));
    }

    @Override
    public void updateSubscription(Subscription subscription) {
        SubscriptionDao.getInstance().updateItem(subscription);
    }

    @Override
    public void deleteSubscription(Long subscriptionId) {
        SubscriptionDao.getInstance().deleteItem(subscriptionId);
    }

    @Override
    public List<Object> getUserSubscriptions(Long userId) {
        return new ArrayList<>(Arrays.asList(SubscriptionDao.getInstance().getItems().stream()
                .filter(o -> o.getUser().getId().equals(userId)).toArray()));
    }

}
