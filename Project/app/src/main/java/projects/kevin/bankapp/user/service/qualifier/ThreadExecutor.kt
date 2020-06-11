package projects.kevin.bankapp.user.service.qualifier

import io.reactivex.Scheduler

class ThreadExecutor(val scheduler: Scheduler)