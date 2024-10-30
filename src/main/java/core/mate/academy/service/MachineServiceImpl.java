package core.mate.academy.service;

import core.mate.academy.model.Bulldozer;
import core.mate.academy.model.Excavator;
import core.mate.academy.model.Machine;
import core.mate.academy.model.Truck;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Your implementation of MachineService.
 */
public class MachineServiceImpl<T extends Machine> implements MachineService<T> {
    private final Map<Class<? extends Machine>, MachineProducer<? extends Machine>> producerMap
            = new HashMap<>();

    {
        producerMap.put(Truck.class, new TruckProducer());
        producerMap.put(Bulldozer.class, new BulldozerProducer());
        producerMap.put(Excavator.class, new ExcavatorProducer());
    }

    @Override
    public List<T> getAll(Class<? extends T> type) {
        MachineProducer<? extends Machine> producer = producerMap.get(type);
        if (producer != null) {
            return (List<T>) producer.get();
        }
        return new ArrayList<>();
    }

    @Override
    public void fill(List<? super T> machines, T value) {
        for (int i = 0; i < machines.size(); i++) {
            machines.set(i, value);
        }
    }

    @Override
    public void startWorking(List<? extends T> machines) {
        for (T machine : machines) {
            machine.doWork();
        }
    }
}
