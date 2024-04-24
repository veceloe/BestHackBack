package mirea.enjoyers.BestHackBack.Services;

import mirea.enjoyers.BestHackBack.Models.Push;

import java.util.List;

public interface PushService {

    List<Push> listPushes(String title);

    Push getPushById(long id);

    void savePush(Push push);

    void deletePush(Long id);

}