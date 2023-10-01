package mmtr.web.service.type;

import mmtr.web.db.entity.TypeEntity;
import mmtr.web.db.repo.type.TypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class TypeServiceImpl implements TypeService {

    private TypeRepository typeRepository;

    @Autowired
    public TypeServiceImpl(TypeRepository typeRepository) {
        this.typeRepository = typeRepository;
    }

    @Override
    public List<String> getTypes() {
        List<String> names = new ArrayList<>();

        for (TypeEntity typeEntity : typeRepository.getAllTypes())
            names.add(typeEntity.getName());

        return names;
    }
}
