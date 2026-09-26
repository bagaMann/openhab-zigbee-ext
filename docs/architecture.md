# Архитектура openHAB Zigbee Ext

## Задача

Проект добавляет пользовательские Zigbee-конвертеры к штатному openHAB Zigbee binding, не заменяя сам binding и не реализуя собственный Zigbee stack.

## Найденная точка расширения

Штатный binding публикует интерфейс:

`org.openhab.binding.zigbee.converter.ZigBeeChannelConverterProvider`

Провайдер может быть зарегистрирован как OSGi service и возвращает отображение `ChannelTypeUID -> Class<? extends ZigBeeBaseChannelConverter>`.

Это позволяет построить отдельный bundle:

```text
Zigbee device
    |
    v
openHAB Zigbee binding
    |
    +-- standard converters
    |
    +-- ZigBeeChannelConverterProvider (OSGi)
            ^
            |
       openhab-zigbee-ext
            |
            +-- custom converters
```

## Этап 1 — PoC

1. Собрать минимальный OSGi bundle для openHAB 5.2.1.
2. Зарегистрировать собственный `ZigBeeChannelConverterProvider`.
3. Проверить, что штатная `ZigBeeChannelConverterFactory` видит провайдер.
4. Добавить минимальный тестовый converter.
5. Установить JAR в тестовый openHAB и проверить жизненный цикл bundle.

На этом этапе WS90-specific обработка ещё не является целью. Сначала проверяется механизм расширения.

## Этап 2 — Shelly WS90

После успешного PoC:

1. Получить fingerprint реального WS90 через CLI Zigbee binding.
2. Зафиксировать endpoints, input/output clusters и attributes.
3. Сопоставить реальные данные с реализацией WS90 в zigbee-herdsman-converters.
4. Реализовать необходимые channels/converters.
5. Проверить reporting и обновление Item state.

## Этап 3 — External Device Definitions

Конечная цель — вынести device-specific mapping из Java в декларативные определения, например:

```text
/etc/openhab/zigbee-ext/
  devices/
    shelly-ws90.yaml
    custom-device.yaml
  clusters/
    shelly.yaml
```

Планируемые возможности:

- идентификация manufacturer/model;
- endpoint/cluster/attribute mapping;
- manufacturer-specific clusters;
- масштабирование и простые transformations;
- dynamic channels;
- чтение attributes/reporting;
- запись attributes и ZCL commands;
- диагностический raw ZCL режим;
- reload definitions без пересборки bundle.

## Принцип

Zigbee network management, coordinator transport, security, discovery и основной ZCL stack остаются ответственностью штатного openHAB Zigbee binding и используемой им библиотеки Zigbee. `openhab-zigbee-ext` расширяет только слой преобразования Zigbee данных в openHAB Channels.
