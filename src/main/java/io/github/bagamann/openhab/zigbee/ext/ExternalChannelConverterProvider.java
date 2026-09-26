package io.github.bagamann.openhab.zigbee.ext;

import java.util.Collections;
import java.util.Map;

import org.openhab.binding.zigbee.converter.ZigBeeBaseChannelConverter;
import org.openhab.binding.zigbee.converter.ZigBeeChannelConverterProvider;
import org.openhab.core.thing.type.ChannelTypeUID;
import org.osgi.service.component.annotations.Component;

/**
 * External Zigbee converter provider.
 *
 * <p>The first PoC intentionally publishes an empty converter map. Its purpose
 * is to prove that a separately installed OSGi bundle can resolve against the
 * official Zigbee binding and register a ZigBeeChannelConverterProvider service.
 */
@Component(immediate = true, service = ZigBeeChannelConverterProvider.class)
public final class ExternalChannelConverterProvider implements ZigBeeChannelConverterProvider {

    @Override
    public Map<ChannelTypeUID, Class<? extends ZigBeeBaseChannelConverter>> getChannelConverters() {
        return Collections.emptyMap();
    }
}
