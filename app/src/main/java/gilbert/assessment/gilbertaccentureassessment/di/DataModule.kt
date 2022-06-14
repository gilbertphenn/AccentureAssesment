package gilbert.assessment.gilbertaccentureassessment.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import gilbert.assessment.gilbertaccentureassessment.repository.DataRepoSource
import gilbert.assessment.gilbertaccentureassessment.repository.DataRepository
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class DataModule {
    @Binds
    @Singleton
    abstract fun provideDataRepository(dataRepository: DataRepository): DataRepoSource
}