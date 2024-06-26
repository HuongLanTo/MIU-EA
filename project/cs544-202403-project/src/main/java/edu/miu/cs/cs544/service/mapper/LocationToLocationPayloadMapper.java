package edu.miu.cs.cs544.service.mapper;

import edu.miu.common.service.mapper.BaseMapper;
import edu.miu.cs.cs544.domain.Location;
import edu.miu.cs.cs544.service.contract.LocationPayload;
import ma.glasnost.orika.MapperFactory;
import org.springframework.stereotype.Component;

@Component
public class LocationToLocationPayloadMapper extends BaseMapper<Location, LocationPayload> {
    public LocationToLocationPayloadMapper(MapperFactory mapperFactory) {
        super(mapperFactory, Location.class, LocationPayload.class);
    }
}
