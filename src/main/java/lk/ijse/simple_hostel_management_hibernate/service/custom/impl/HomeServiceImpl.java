package lk.ijse.simple_hostel_management_hibernate.service.custom.impl;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import lk.ijse.simple_hostel_management_hibernate.config.SessionFactoryConfig;
import lk.ijse.simple_hostel_management_hibernate.projection.CustomProjection;
import lk.ijse.simple_hostel_management_hibernate.repository.RepositoryFactory;
import lk.ijse.simple_hostel_management_hibernate.repository.custom.QueryRepository;
import lk.ijse.simple_hostel_management_hibernate.service.custom.HomeService;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

public class HomeServiceImpl implements HomeService {
    @Override
    public ObservableList<CustomProjection> getDetailsToTableView() {

        QueryRepository queryRepository = RepositoryFactory.getRepositoryFactory()
                .getRepository(RepositoryFactory.RepositoryTypes.QUERY);

        Session session = SessionFactoryConfig.getInstance().getSession();
        Transaction transaction =session.beginTransaction();
        try {
            queryRepository.setSession(session);
            List<CustomProjection> customList = queryRepository.getDetailsOfStudentsWithoutKeyMoney();
            ObservableList<CustomProjection> customObList = FXCollections.observableArrayList(customList);

            transaction.commit();
            session.close();
            return customObList;
        }catch (Exception e){
            transaction.rollback();
            session.close();
            System.out.println("getDetailsOfStudentsWithoutKeyMoney failed");
            System.out.println(e);
            return null;
        }
    }
}
