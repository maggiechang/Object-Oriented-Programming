import java.util.List;
import processing.core.PImage;
import java.util.*;

final class Entity
{
   private EntityKind kind;
   private String id;
   private Point position;
   private List<PImage> images;
   private int imageIndex;
   private int resourceLimit;
   private int resourceCount;
   private int actionPeriod;
   private int animationPeriod;

   private static final int QUAKE_ANIMATION_REPEAT_COUNT = 10;
   private static final String QUAKE_KEY = "quake";

   private static final String ORE_ID_PREFIX = "ore -- ";
   private static final int ORE_CORRUPT_MIN = 20000;
   private static final int ORE_CORRUPT_MAX = 30000;
   private static final String ORE_KEY = "ore";

   private static final Random rand = new Random();

   private static final String BLOB_KEY = "blob";
   private static final String BLOB_ID_SUFFIX = " -- blob";
   private static final int BLOB_PERIOD_SCALE = 4;
   private static final int BLOB_ANIMATION_MIN = 50;
   private static final int BLOB_ANIMATION_MAX = 150;

   public Point getPosition() {
      return position;
   }
   public void setPosition(Point position) {
      this.position = position;
   }
   public EntityKind getKind() {
      return kind;
   }
   public List<PImage> getImages() {
      return images;
   }
   public int getImageIndex() {
      return imageIndex;
   }


   public Entity(EntityKind kind, String id, Point position,
      List<PImage> images, int resourceLimit, int resourceCount,
      int actionPeriod, int animationPeriod)
   {
      this.kind = kind;
      this.id = id;
      this.position = position;
      this.images = images;
      this.imageIndex = 0;
      this.resourceLimit = resourceLimit;
      this.resourceCount = resourceCount;
      this.actionPeriod = actionPeriod;
      this.animationPeriod = animationPeriod;
   }
   public int getAnimationPeriod()
   {
      switch (this.kind)
      {
         case MINER_FULL:
         case MINER_NOT_FULL:
         case ORE_BLOB:
         case QUAKE:
            return this.animationPeriod;
         default:
            throw new UnsupportedOperationException(
                    String.format("getAnimationPeriod not supported for %s",
                            this.kind));
      }
   }
   public Point nextPositionMiner(WorldModel world,
                                         Point destPos)
   {
      int horiz = Integer.signum(destPos.x - this.position.x);
      Point newPos = new Point(this.position.x + horiz,
              this.position.y);

      if (horiz == 0 || world.isOccupied(newPos))
      {
         int vert = Integer.signum(destPos.y - this.position.y);
         newPos = new Point(this.position.x,
                 this.position.y + vert);

         if (vert == 0 || world.isOccupied(newPos))
         {
            newPos = this.position;
         }
      }

      return newPos;
   }
   public Action createActivityAction(WorldModel world,
                                             ImageStore imageStore)
   {
      return new Action(ActionKind.ACTIVITY, this, world, imageStore, 0);
   }
   public void scheduleActions(EventScheduler scheduler,
                                      WorldModel world, ImageStore imageStore)
   {
      switch (this.kind)
      {
         case MINER_FULL:
            scheduleEvent(scheduler,
                    this.createActivityAction(world, imageStore),
                    this.actionPeriod);
            scheduleEvent(scheduler, createAnimationAction(0),
                    this.getAnimationPeriod());
            break;

         case MINER_NOT_FULL:
            scheduleEvent(scheduler,
                    this.createActivityAction(world, imageStore),
                    this.actionPeriod);
            scheduleEvent(scheduler,
                    createAnimationAction(0), this.getAnimationPeriod());
            break;

         case ORE:
            scheduleEvent(scheduler,
                    this.createActivityAction(world, imageStore),
                    this.actionPeriod);
            break;

         case ORE_BLOB:
            scheduleEvent(scheduler,
                    this.createActivityAction(world, imageStore),
                    this.actionPeriod);
            scheduleEvent(scheduler,
                    createAnimationAction(0), this.getAnimationPeriod());
            break;

         case QUAKE:
            scheduleEvent(scheduler,
                    this.createActivityAction(world, imageStore),
                    this.actionPeriod);
            scheduleEvent(scheduler,
                    createAnimationAction(QUAKE_ANIMATION_REPEAT_COUNT),
                    this.getAnimationPeriod());
            break;

         case VEIN:
            scheduleEvent(scheduler,
                    this.createActivityAction(world, imageStore),
                    this.actionPeriod);
            break;

         default:
      }
   }
   public void scheduleEvent(EventScheduler scheduler,
                                    Action action, long afterPeriod)
   {
      long time = System.currentTimeMillis() +
              (long)(afterPeriod * scheduler.getTimeScale());
      Event event = new Event(action, time, this);

      scheduler.getEventQueue().add(event);

      // update list of pending events for the given entity
      List<Event> pending = scheduler.getPendingEvents().getOrDefault(this,
              new LinkedList<>());
      pending.add(event);
      scheduler.getPendingEvents().put(this, pending);
   }
   public Action createAnimationAction(int repeatCount)
   {
      return new Action(ActionKind.ANIMATION, this, null, null, repeatCount);
   }
   public void nextImage()
   {
      this.imageIndex = (this.imageIndex + 1) % this.images.size();
   }
   public void executeMinerFullActivity(WorldModel world,
                                               ImageStore imageStore, EventScheduler scheduler)
   {
      Optional<Entity> fullTarget = world.findNearest(this.position,
              EntityKind.BLACKSMITH);

      if (fullTarget.isPresent() &&
              moveToFull(world, fullTarget.get(), scheduler))
      {
         this.transformFull(world, scheduler, imageStore);
      }
      else
      {
         this.scheduleEvent(scheduler,
                 this.createActivityAction(world, imageStore),
                 this.actionPeriod);
      }
   }
   public boolean moveToFull(WorldModel world,
                                    Entity target, EventScheduler scheduler)
   {
      if (Functions.adjacent(this.position, target.position))
      {
         return true;
      }
      else
      {
         Point nextPos = this.nextPositionMiner(world, target.position);

         if (!this.position.equals(nextPos))
         {
            Optional<Entity> occupant = world.getOccupant(nextPos);
            if (occupant.isPresent())
            {
               scheduler.unscheduleAllEvents(occupant.get());
            }

            world.moveEntity(this, nextPos);
         }
         return false;
      }
   }
   public void executeMinerNotFullActivity(WorldModel world, ImageStore imageStore, EventScheduler scheduler)
   {
      Optional<Entity> notFullTarget = world.findNearest(this.position,
              EntityKind.ORE);

      if (!notFullTarget.isPresent() ||
              !this.moveToNotFull(world, notFullTarget.get(), scheduler) ||
              !this.transformNotFull(world, scheduler, imageStore))
      {
         this.scheduleEvent(scheduler,
                 this.createActivityAction(world, imageStore),
                 this.actionPeriod);
      }
   }
   public void executeOreActivity(WorldModel world,
                                         ImageStore imageStore, EventScheduler scheduler)
   {
      Point pos = this.position;  // store current position before removing

      world.removeEntity(this);
      scheduler.unscheduleAllEvents(this);

      Entity blob = pos.createOreBlob(this.id + BLOB_ID_SUFFIX, this.actionPeriod / BLOB_PERIOD_SCALE,
              BLOB_ANIMATION_MIN +
                      rand.nextInt(BLOB_ANIMATION_MAX - BLOB_ANIMATION_MIN),
              imageStore.getImageList(BLOB_KEY));

      world.addEntity(blob);
      blob.scheduleActions(scheduler, world, imageStore);
   }

   public void executeOreBlobActivity(WorldModel world,
                                             ImageStore imageStore, EventScheduler scheduler)
   {
      Optional<Entity> blobTarget = world.findNearest(
              this.position, EntityKind.VEIN);
      long nextPeriod = this.actionPeriod;

      if (blobTarget.isPresent())
      {
         Point tgtPos = blobTarget.get().position;

         if (this.moveToOreBlob(world, blobTarget.get(), scheduler))
         {
            Entity quake = tgtPos.createQuake(imageStore.getImageList(QUAKE_KEY));

            world.addEntity(quake);
            nextPeriod += this.actionPeriod;
            quake.scheduleActions(scheduler, world, imageStore);
         }
      }

      this.scheduleEvent(scheduler,
              this.createActivityAction(world, imageStore),
              nextPeriod);
   }
   public void executeQuakeActivity(WorldModel world,
                                           ImageStore imageStore, EventScheduler scheduler)
   {
      scheduler.unscheduleAllEvents(this);
      world.removeEntity(this);
   }
   public void executeVeinActivity(WorldModel world,
                                          ImageStore imageStore, EventScheduler scheduler)
   {
      Optional<Point> openPt = world.findOpenAround(this.position);

      if (openPt.isPresent())
      {
         Entity ore = openPt.get().createOre(ORE_ID_PREFIX + this.id, ORE_CORRUPT_MIN +
                         rand.nextInt(ORE_CORRUPT_MAX - ORE_CORRUPT_MIN),
                 imageStore.getImageList(ORE_KEY));
         world.addEntity(ore);
         ore.scheduleActions(scheduler, world, imageStore);
      }

      this.scheduleEvent(scheduler,
              this.createActivityAction(world, imageStore),
              this.actionPeriod);
   }
   public Point nextPositionOreBlob(WorldModel world,
                                           Point destPos)
   {
      int horiz = Integer.signum(destPos.x - this.position.x);
      Point newPos = new Point(this.position.x + horiz,
              this.position.y);

      Optional<Entity> occupant = world.getOccupant(newPos);

      if (horiz == 0 ||
              (occupant.isPresent() && !(occupant.get().kind == EntityKind.ORE)))
      {
         int vert = Integer.signum(destPos.y - this.position.y);
         newPos = new Point(this.position.x, this.position.y + vert);
         occupant = world.getOccupant(newPos);

         if (vert == 0 ||
                 (occupant.isPresent() && !(occupant.get().kind == EntityKind.ORE)))
         {
            newPos = this.position;
         }
      }

      return newPos;
   }
   public boolean moveToOreBlob(WorldModel world,
                                       Entity target, EventScheduler scheduler)
   {
      if (Functions.adjacent(this.position, target.position))
      {
         world.removeEntity(target);
         scheduler.unscheduleAllEvents(target);
         return true;
      }
      else
      {
         Point nextPos = this.nextPositionOreBlob(world, target.position);

         if (!this.position.equals(nextPos))
         {
            Optional<Entity> occupant = world.getOccupant(nextPos);
            if (occupant.isPresent())
            {
               scheduler.unscheduleAllEvents(occupant.get());
            }

            world.moveEntity(this, nextPos);
         }
         return false;
      }
   }
   public boolean transformNotFull(WorldModel world,
                                          EventScheduler scheduler, ImageStore imageStore)
   {
      if (this.resourceCount >= this.resourceLimit)
      {
         Entity miner = this.position.createMinerFull(this.id, this.resourceLimit, this.actionPeriod, this.animationPeriod,
                 this.images);

         world.removeEntity(this);
         scheduler.unscheduleAllEvents(this);

         world.addEntity(miner);
         miner.scheduleActions(scheduler, world, imageStore);

         return true;
      }

      return false;
   }
   public boolean moveToNotFull(WorldModel world,
                                       Entity target, EventScheduler scheduler)
   {
      if (Functions.adjacent(this.position, target.position))
      {
         this.resourceCount += 1;
         world.removeEntity(target);
         scheduler.unscheduleAllEvents(target);

         return true;
      }
      else
      {
         Point nextPos = this.nextPositionMiner(world, target.position);

         if (!this.position.equals(nextPos))
         {
            Optional<Entity> occupant = world.getOccupant(nextPos);
            if (occupant.isPresent())
            {
               scheduler.unscheduleAllEvents(occupant.get());
            }

            world.moveEntity(this, nextPos);
         }
         return false;
      }
   }
   public void transformFull(WorldModel world,
                                    EventScheduler scheduler, ImageStore imageStore)
   {
      Entity miner = this.position.createMinerNotFull(this.id, this.resourceLimit, this.actionPeriod, this.animationPeriod,
              this.images);

      world.removeEntity(this);
      scheduler.unscheduleAllEvents(this);

      world.addEntity(miner);
      miner.scheduleActions(scheduler, world, imageStore);
   }

}
