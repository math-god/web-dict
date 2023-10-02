package mmtr.web.db.repo.key;

import mmtr.web.db.entity.KeyEntity;
import mmtr.web.db.repo.base.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public class KeyRepositoryImpl extends CrudRepository<KeyEntity> implements KeyRepository {

}
