import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;

final class EventScheduler
{
   private PriorityQueue<Event> eventQueue;
   private Map<Entity, List<Event>> pendingEvents;
   private double timeScale;

   public PriorityQueue<Event> getEventQueue() {
      return eventQueue;
   }
   public Map<Entity, List<Event>> getPendingEvents() {
      return pendingEvents;
   }
   public double getTimeScale() {
      return timeScale;
   }

   public EventScheduler(double timeScale)
   {
      this.eventQueue = new PriorityQueue<>(new EventComparator());
      this.pendingEvents = new HashMap<>();
      this.timeScale = timeScale;
   }
   public void unscheduleAllEvents(Entity entity)
   {
      List<Event> pending = this.pendingEvents.remove(entity);

      if (pending != null)
      {
         for (Event event : pending)
         {
            this.eventQueue.remove(event);
         }
      }
   }
   public void removePendingEvent(Event event)
   {
      List<Event> pending = this.pendingEvents.get(event.getEntity());

      if (pending != null)
      {
         pending.remove(event);
      }
   }
   public void updateOnTime(long time)
   {
      while (!this.eventQueue.isEmpty() &&
              this.eventQueue.peek().getTime() < time)
      {
         Event next = this.eventQueue.poll();

         this.removePendingEvent(next);

         next.getAction().executeAction(this);
      }
   }
}
