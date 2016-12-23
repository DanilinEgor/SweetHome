package xyz.egor_d.sweethome

import dagger.Component
import xyz.egor_d.sweethome.view.NewsFragment
import javax.inject.Singleton

@Singleton
@Component(modules = arrayOf(AppModule::class))
interface AppComponent {
    fun inject(newsFragment: NewsFragment)
}