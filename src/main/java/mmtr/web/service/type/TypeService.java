package mmtr.web.service.type;

import mmtr.web.db.repo.type.TypeRepository;
import org.springframework.stereotype.Service;

import java.util.List;

public interface TypeService {
    List<String> getTypes();
}
