package org.neverhook.client.event.events;

public interface Cancellable {

    boolean isCancelled();

    void setCancelled(boolean state);

}
