package com.pulse.ecommerce.repository;



import com.pulse.ecommerce.model.Delivery;
import com.pulse.ecommerce.model.UserRecord;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface DeliveryRepository extends JpaRepository<Delivery, Long> {
    List<Delivery> findByUser(UserRecord user);

}
